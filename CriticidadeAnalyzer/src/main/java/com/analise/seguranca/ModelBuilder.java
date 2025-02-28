package com.analise.seguranca;

import com.analise.seguranca.model.ASTNode;
import com.analise.seguranca.model.CallGraphEdge;
import com.analise.seguranca.model.LibraryDependency;
import com.analise.seguranca.model.RootAST;

import java.util.List;

public class ModelBuilder {

    public RootAST buildModel(String sourceCodePath, String callGraphPath) {
        RootAST root = new RootAST();
        root.setProjectName("MeuProjetoSeguranca");

        // 1) AST Nodes
        List<ASTNode> astNodes = ASTAnalyzer.analyzeAST(sourceCodePath);
        root.getAstNodes().addAll(astNodes);

        // 2) Call Graph Edges
        List<CallGraphEdge> edges = CallGraphAnalyzer.processCallGraph(callGraphPath);
        root.getCallGraphEdges().addAll(edges);

        // 3) (Opcional) bibliotecas
        //    Se quiser parsear do pom.xml, do build.gradle, ou do OWASP,
        //    basta criar outro método e popular. Exemplo fictício:
        // List<LibraryDependency> libs = LibraryScanner.scanDependencies(sourceCodePath);
        // root.getLibraries().addAll(libs);

        return root;
    }
}
