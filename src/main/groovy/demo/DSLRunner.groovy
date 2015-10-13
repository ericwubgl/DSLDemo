package demo

class DSLRunner {

    static void main(String[] args) {
        def samples = [1,2,3,4,5,6,7,'I',8,'am',9,'genius', '3/6/2015']
        Script dslScript = new GroovyShell().parse(new File('src/main/groovy/demo/dsl.groovy'))
        dslScript.metaClass = createEMC(dslScript.class, {
            ExpandoMetaClass emc ->
            emc.dataImport = { Closure cl ->
                def dataImport = new DataImport(samples)
                def code = cl.rehydrate(dataImport, this, this)
                code.resolveStrategy = Closure.DELEGATE_ONLY
                code()
            }
        })
        dslScript.run()
    }

    static ExpandoMetaClass createEMC(Class clazz, Closure cl) {
        ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
        cl(emc)
        emc.initialize()
        return emc
    }

}