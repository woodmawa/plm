package com.softwood.bootstrap.applicationHelper

import groovy.transform.SourceURI

import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ConcurrentLinkedQueue

class $ApplicationInit {
    /**
     * internal helper class to help set up the main Application when run
     */
        //internal Helper methods - establishes some initial variables on an Expando
        static  setupBinding(Binding appBinding) {
            appBinding.with {
                $runScriptOrder = new ConcurrentLinkedQueue<>()
                //add container for all the portfolio list entities for reuse
                //todo not sure if Expand is thread safe may need synchronistaion add method to protect
                //todo could protect with an agent
                vfPortfolio = new Expando()
            }
           assert appBinding.vfPortfolio instanceof Expando
        }

        //internal Helper methods
        static executeBootstrapConfig(Binding appBinding) {

            @SourceURI URI sourceUri
            Path classFileLocation = Paths.get(sourceUri).parent
            Path bootstrapLocation = classFileLocation.parent // go out to bootstrap 'home'

            def bootstrap = new File("$bootstrapLocation/bootstrapConfig.groovy")

            def configFileName = bootstrap.canonicalPath

            if (bootstrap.exists() && bootstrap.canExecute()) {
                GroovyShell shell = new GroovyShell(appBinding)
                shell.evaluate(bootstrap)      //run the bootstrap.groovy
            }

        }

}