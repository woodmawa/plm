package com.softwood.bootstrap

import groovy.transform.SourceURI

import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ConcurrentLinkedQueue


class Application {

    static appBinding = new Binding()

    static main (args) {
        setupBinding ()
        executeBootstrapConfig()

        //global variables from binding now visible
        appBinding.with {
            println ">> $vfPortfolio.productsMaster"
        }

    }

    //Helper methods - establishes some initial variables on an Expando
    static setupBinding () {
        appBinding.with {
            $runScriptOrder = new ConcurrentLinkedQueue<>()
            //add container for all the portfolio list entities for reuse
            //todo not sure if Expand is thread safe may need synchronistaion add method to protect
            vfPortfolio = new Expando()
        }
    }

    //Helper methods
    static executeBootstrapConfig () {

        @SourceURI URI sourceUri
        Path scriptLocation = Paths.get(sourceUri).parent

        def bootstrap = new File ("$scriptLocation/bootstrapConfig.groovy")

        def cfn = bootstrap.canonicalPath

        if (bootstrap.exists() && bootstrap.canExecute()) {
            GroovyShell shell = new GroovyShell(appBinding)
            shell.evaluate (bootstrap)      //run the bootstrap.groovy
        }

    }
}
