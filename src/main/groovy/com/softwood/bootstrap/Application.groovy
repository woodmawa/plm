package com.softwood.bootstrap

import com.softwood.bootstrap.applicationHelper.*

@Singleton
class Application {

    static Binding appBinding = new Binding()

    static Closure pre
    static Closure post

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
            //println ">> ${vfPortfolio.productMaster}"
            println ">> ${vfPortfolio.productUses.list()} "
            println ">> ${vfPortfolio.productMaster.list()} "
            println ">> ${vfPortfolio.productHierarchies.list()} "
        }


    }

    /**
     * static initialiser block to ensure that binding and bootstrap are executed
     */
    static {
        if (pre)
            pre()

        //run initial config
        $ApplicationInit.setupBinding (appBinding)
        $ApplicationInit.executeBootstrapConfig(appBinding)

        if (post)
            post ()
    }


}
