package com.analise.seguranca.util;

import com.analise.seguranca.model.RootAST;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class ModelUtil {

    /**
     * Converte um objeto RootAST em um EObject usando um EPackage e EClass dinâmicos.
     * Esta implementação mapeia, por enquanto, apenas o atributo "projectName".
     */
    public static EObject convertToEObject(RootAST root, ResourceSet resourceSet) {
        EcoreFactory factory = EcoreFactory.eINSTANCE;
        // Cria um pacote dinâmico
        EPackage dynamicPackage = factory.createEPackage();
        dynamicPackage.setName("dynamicRootPackage");
        dynamicPackage.setNsPrefix("drp");
        dynamicPackage.setNsURI("http://example.org/dynamicRootPackage");

        // Cria uma EClass para RootAST
        EClass rootEClass = factory.createEClass();
        rootEClass.setName("RootAST");
        dynamicPackage.getEClassifiers().add(rootEClass);

        // Cria um EAttribute para projectName
        EAttribute projectNameAttr = factory.createEAttribute();
        projectNameAttr.setName("projectName");
        projectNameAttr.setEType(EcorePackage.Literals.ESTRING);
        rootEClass.getEStructuralFeatures().add(projectNameAttr);

        // Registra o pacote na ResourceSet
        resourceSet.getPackageRegistry().put(dynamicPackage.getNsURI(), dynamicPackage);

        // Cria a instância do EObject
        EObject rootEObject = dynamicPackage.getEFactoryInstance().create(rootEClass);
        rootEObject.eSet(rootEClass.getEStructuralFeature("projectName"), root.getProjectName());

        return rootEObject;
    }
}
