package com.analise.seguranca;

import java.util.Set;
import java.util.regex.Pattern;

public class HeuristicsChecker {
    private static final Pattern HARD_CODED_CREDENTIALS = Pattern.compile("(?i)(password|secret|api[_-]?key|token|passwd|pwd)");

    public static void applyHeuristics() {
        /*Set<String> issues = ASTAnalyzer.getDetectedIssues();

        for (String issue : issues) {
            if (HARD_CODED_CREDENTIALS.matcher(issue).find()) {
                System.out.println("🚨 H1 Violation: Credencial embutida detectada → " + issue);
            }
            // Aqui adicionamos outras heurísticas (H2 a H6)
        }*/
    }
}
