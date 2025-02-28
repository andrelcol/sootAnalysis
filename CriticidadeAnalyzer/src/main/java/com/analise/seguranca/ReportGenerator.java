package com.analise.seguranca;

import com.analise.seguranca.model.RootAST;
import com.analise.seguranca.util.ModelUtil;

import org.eclipse.acceleo.engine.service.AcceleoService;
import org.eclipse.acceleo.model.mtl.Module;
import org.eclipse.acceleo.model.mtl.MtlPackage;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReportGenerator {

    public static void main(String[] args) {
        // Exemplo: Crie um RootAST (pode ser construído via ModelBuilder)
        RootAST root = new RootAST();
        root.setProjectName("MeuProjetoSeguranca");
        // Aqui você preencheria as listas (ASTNodes, CallGraphEdges, etc.)

        generateReport(root);
    }

    public static void generateReport(RootAST rootAST) {
        System.out.println("📊 Iniciando geração do relatório via AcceleoService...");

        try {
            // 1. Configuração do ResourceSet para EMF
            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                       .put("xmi", new XMIResourceFactoryImpl());
            // Registre o pacote MTL para que o Acceleo reconheça os templates
            registerMtlPackage(resourceSet);

            // 2. Converta o seu modelo (RootAST) em um EObject.
            // A implementação de ModelUtil.convertToEObject deve mapear os dados de RootAST para um EObject.
            EObject rootEObject = ModelUtil.convertToEObject(rootAST, resourceSet);

            // 3. (Opcional) Salve o modelo em um arquivo XMI para depuração.
            File modelFile = new File("modelo.xmi");
            Resource modelResource = resourceSet.createResource(URI.createFileURI(modelFile.getAbsolutePath()));
            modelResource.getContents().add(rootEObject);
            modelResource.save(Collections.emptyMap());
            System.out.println("✅ Modelo XMI salvo em: " + modelFile.getAbsolutePath());

            // 4. Carregue o template Acceleo (garanta que o arquivo esteja em src/main/resources)
            File templateFile = new File("src/main/resources/heuristicReport.mtl");
            if (!templateFile.exists()) {
                throw new RuntimeException("❌ Template não encontrado: " + templateFile.getAbsolutePath());
            }
            URI templateURI = URI.createFileURI(templateFile.getAbsolutePath());
            Resource templateResource = resourceSet.getResource(templateURI, true);
            if (templateResource == null || templateResource.getContents().isEmpty()) {
                throw new RuntimeException("❌ Falha ao carregar o template: " + templateFile.getAbsolutePath());
            }

            // 5. Obtenha o módulo Acceleo do template
            Module module = (Module) templateResource.getContents().get(0);
            Map<Module, Set<String>> modules = new HashMap<>();
            modules.put(module, Collections.emptySet());

            // 6. Defina o arquivo de saída
            File outputFile = new File("relatorio_analise.txt");

            // 7. Crie o AcceleoService e execute a geração
            AcceleoService service = new AcceleoService();
            service.doGenerate(
                modules,
                rootEObject,   // Modelo convertido
                outputFile,    // Arquivo de saída
                new BasicMonitor()
            );

            System.out.println("✅ Relatório gerado com sucesso em: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Erro durante a geração do relatório:");
            e.printStackTrace();
        }
    }

    private static void registerMtlPackage(ResourceSet resourceSet) {
        try {
            Class<?> mtlFactoryClass = Class.forName("org.eclipse.acceleo.model.mtl.resource.MtlResourceFactoryImpl");
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                       .put("mtl", mtlFactoryClass.getDeclaredConstructor().newInstance());
            System.out.println("✅ MtlResourceFactory registrado com sucesso.");
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao registrar MtlResourceFactory: " + e.getMessage());
        }
        // Registre o pacote MTL no PackageRegistry
        resourceSet.getPackageRegistry().put(MtlPackage.eNS_URI, MtlPackage.eINSTANCE);
    }
}
