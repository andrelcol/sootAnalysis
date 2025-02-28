package com.analise.seguranca;

import com.analise.seguranca.model.RootAST;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java -jar CriticidadeAnalyzer.jar <caminho_codigo> <callgraph.dot>");
            return;
        }

        String codigoPath = args[0];
        String callGraphPath = args[1];

        System.out.println("Iniciando análise...");
        System.out.println("Código-fonte: " + codigoPath);
        System.out.println("Call Graph: " + callGraphPath);

        try {
            // Constrói o modelo unificado
            ModelBuilder builder = new ModelBuilder();
            RootAST root = builder.buildModel(codigoPath, callGraphPath);

            // Gera relatório via Acceleo
            System.out.println("Gerando relatório com Acceleo...");
            ReportGenerator.generateReport(root);
            System.out.println("Relatório gerado com sucesso.");

            System.out.println("\nAnálise concluída! Verifique o relatório 'relatorio_analise.txt'.");
        } catch (Exception e) {
            System.err.println("Erro durante a análise: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
