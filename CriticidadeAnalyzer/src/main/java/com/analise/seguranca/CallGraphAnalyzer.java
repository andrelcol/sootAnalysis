package com.analise.seguranca;

import com.analise.seguranca.model.CallGraphEdge;
import java.io.*;
import java.util.*;

public class CallGraphAnalyzer {

    public static List<CallGraphEdge> processCallGraph(String filePath) {
        List<CallGraphEdge> edges = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("->")) {
                    // Ex: "Caller" -> "Callee"
                    String[] parts = line.replace("\"", "").split("->");
                    if (parts.length == 2) {
                        String caller = parts[0].trim();
                        String callee = parts[1].replace(";", "").trim();

                        CallGraphEdge edge = new CallGraphEdge();
                        edge.setCaller(caller);
                        edge.setCallee(callee);

                        // Se quisermos heurística simples de "sensível"
                        if (callee.toLowerCase().contains("exec") 
                            || callee.toLowerCase().contains("deleteuser")) {
                            edge.setSensitive(true);
                        }

                        edges.add(edge);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("🔍 Funções detectadas no Call Graph: " + edges.size() + " edges.");
        return edges;
    }
}
