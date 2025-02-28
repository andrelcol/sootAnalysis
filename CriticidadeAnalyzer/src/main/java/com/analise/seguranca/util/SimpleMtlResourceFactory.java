package com.analise.seguranca.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;


public class SimpleMtlResourceFactory extends ResourceFactoryImpl {
    @Override
    public Resource createResource(URI uri) {
        // Essa implementação cria um recurso XML para o arquivo .mtl.
        return new XMLResourceImpl(uri);
    }
}
