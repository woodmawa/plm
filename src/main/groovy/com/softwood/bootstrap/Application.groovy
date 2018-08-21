package com.softwood.bootstrap

import com.softwood.bootstrap.applicationHelper.*
import com.softwood.domain.offering.ProductOffering

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
            println ">> ${vfPortfolio.productsMaster.list()} "
            println ">> ${vfPortfolio.productHierarchies.list()} "
            println ">> ${vfPortfolio.productLines.list()} "
            println ">> ${vfPortfolio.salesCataloguesMaster.list()} "
            def iPhoneMap = vfPortfolio.productAttributeMappings.find {it.product.name == "iPhone"}
            assert iPhoneMap
            println iPhoneMap.toString()

            def offerings = vfPortfolio.offeredProductsMaster
            assert vfPortfolio.offeredProductsMaster.size() > 1

            ProductOffering myOffer = offerings[0]
            println "\n myoffer : " + myOffer + ", with commercial attributes "
            println myOffer.offeringAttributeAssignment

            myOffer = offerings[1]
            assert myOffer.offeringAttributeAssignment.offeringAttributeGroupsList.size() == 2
            println "First mps package  offer : " + myOffer
            println myOffer.offeringAttributeAssignment

            println "iphone offer : " + offerings[2]
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
