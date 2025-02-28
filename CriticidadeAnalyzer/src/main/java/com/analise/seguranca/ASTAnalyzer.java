package com.analise.seguranca;

import com.analise.seguranca.model.ASTNode;
import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ASTAnalyzer {

    public static List<ASTNode> analyzeAST(String sourcePath) {
        List<ASTNode> allNodes = new ArrayList<>();

        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            System.out.println("❌ Caminho não encontrado: " + sourcePath);
            return allNodes;
        }

        try {
            if (sourceFile.isFile() && sourcePath.endsWith(".java")) {
                allNodes.addAll(analyzeJavaFile(sourceFile.toPath()));
            } else if (sourceFile.isDirectory()) {
                Files.walk(Paths.get(sourcePath))
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".java"))
                        .forEach(path -> {
                            allNodes.addAll(analyzeJavaFile(path));
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("✅ AST analisada. Nós encontrados: " + allNodes.size());
        return allNodes;
    }

    private static List<ASTNode> analyzeJavaFile(Path filePath) {
        List<ASTNode> nodeList = new ArrayList<>();
        System.out.println("🔍 Analisando arquivo: " + filePath.getFileName());

        try {
            JavaParser javaParser = new JavaParser(
                    new ParserConfiguration()
                        .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_17)
                        .setAttributeComments(false) 
            );

            ParseResult<CompilationUnit> parseResult = javaParser.parse(filePath);
            
            if (parseResult.isSuccessful() && parseResult.getResult().isPresent()) {
                CompilationUnit cu = parseResult.getResult().get();

                // Visit methods
                cu.accept(new VoidVisitorAdapter<Void>() {
                    @Override
                    public void visit(MethodDeclaration md, Void arg) {
                        super.visit(md, arg);
                        ASTNode node = new ASTNode();
                        node.setType("MethodDecl");
                        node.setName(md.getNameAsString());
                        node.setLocation(filePath.getFileName() + ":" + md.getRange()
                                .map(r -> r.begin.line).orElse(-1));
                        nodeList.add(node);
                    }

                    @Override
                    public void visit(StringLiteralExpr sl, Void arg) {
                        super.visit(sl, arg);
                        ASTNode node = new ASTNode();
                        node.setType("StringLiteral");
                        node.setStringValue(sl.getValue());
                        node.setLocation(filePath.getFileName() + ":" + sl.getRange()
                                .map(r -> r.begin.line).orElse(-1));
                        nodeList.add(node);
                    }
                }, null);

                System.out.println("✅ AST gerada para: " + filePath.getFileName()
                                   + " com " + nodeList.size() + " nós.");
            } else {
                System.err.println("⚠ Erro ao processar arquivo: " + filePath.getFileName());
                parseResult.getProblems().forEach(problem -> 
                    System.err.println("❌ " + problem.toString())
                );
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao analisar arquivo " + filePath.getFileName() + ": " + e.getMessage());
        }

        return nodeList;
    }
}
