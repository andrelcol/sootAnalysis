package sootanalysis;

import soot.*;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.options.Options;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class SootBatchProcessor {
    public static void main(String[] args) {
        // Diretório contendo os arquivos .class
        String inputDirectory = "classesClass"; // Certifique-se de que contém os arquivos compilados

        // Configuração do Soot
        G.reset();
        Options.v().set_prepend_classpath(true);
        Options.v().set_process_dir(Collections.singletonList(inputDirectory)); // Processa todos os arquivos da pasta
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_whole_program(true);
        Options.v().set_keep_line_number(true);
        Options.v().set_keep_offset(true);
        Options.v().set_allow_phantom_refs(true); // Evita erro com classes ausentes
        Options.v().setPhaseOption("cg.spark", "enabled:true"); // Ativa Call Graph

        // Carregar as classes
        Scene.v().loadNecessaryClasses();

        // Executar a análise
        PackManager.v().runPacks();

        // Gerar o Call Graph
        exportCallGraphToDot("callgraph.dot");

        System.out.println("Grafo de chamadas gerado com sucesso! Verifique 'callgraph.dot'.");
    }

    private static void exportCallGraphToDot(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("digraph G {\n");

            CallGraph cg = Scene.v().getCallGraph();
            for (Edge edge : cg) {
                writer.write("\"" + edge.src() + "\" -> \"" + edge.tgt() + "\";\n");
            }

            writer.write("}");
            System.out.println("Grafo exportado para " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
