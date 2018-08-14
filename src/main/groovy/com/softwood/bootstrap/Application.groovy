package com.softwood.bootstrap

import com.softwood.bootstrap.applicationHelper.*

@Singleton
class Application {

    static Binding appBinding = new Binding()

    /**
     * main programme entry point with main method
     * @param args
     */
    static main (args) {
        Application.instance.run()
}

    def run (args) {
        //global variables from binding now visible
        appBinding.with {
            println ">> ${vfPortfolio.productsMaster}"
        }


    }

    /**
     * static initialiser block to ensure that binding and bootstrap are executed
     */
    static {
        //run initial config
        $ApplicationInit.setupBinding (appBinding)
        $ApplicationInit.executeBootstrapConfig(appBinding)
    }


}
