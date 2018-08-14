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
            println ">> $vfProductsMaster"
        }

    }

    static setupBinding () {
        appBinding.runScriptOrder = new ConcurrentLinkedQueue<>()
    }

    static executeBootstrapConfig () {

        @SourceURI URI sourceUri
        Path scriptLocation = Paths.get(sourceUri).parent

        def bootstrap = new File ("$scriptLocation/bootstrapConfig.groovy")

        def cfn = bootstrap.canonicalPath

        def ready = bootstrap.exists() //&& bootstrap.canExecute())
        if (ready) {
            GroovyShell shell = new GroovyShell(appBinding)
            shell.evaluate (bootstrap)      //run the bootstrap.groovy
        }

    }
}
