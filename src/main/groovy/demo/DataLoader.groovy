package demo

import java.util.concurrent.TimeUnit

class DataLoader {

    private final static String BT_CONFIG = "grails-app/conf/bt.groovy"

    DataImport dataImport

    def load(def data) {
        Date before = new Date()
        Script dslScript = new GroovyShell().parse(new File(BT_CONFIG))
        Date between = new Date()
        long diff = between.getTime() - before.getTime()
        println TimeUnit.MILLISECONDS.convert(diff, TimeUnit.MILLISECONDS)
        dslScript.metaClass = createEMC(dslScript.class, {
            ExpandoMetaClass emc ->
            emc.dataImport = { Closure cl ->
                dataImport.elements = data
                def code = cl.rehydrate(dataImport, this, this)
                code.resolveStrategy = Closure.DELEGATE_ONLY
                code()
            }
        })
        Date after = new Date()
        diff = after.getTime() - before.getTime()
        println TimeUnit.MILLISECONDS.convert(diff, TimeUnit.MILLISECONDS)
        dslScript.run()
    }

    ExpandoMetaClass createEMC(Class clazz, Closure cl) {
        ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
        cl(emc)
        emc.initialize()
        return emc
    }

}
