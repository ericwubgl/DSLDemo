package demo

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class Transformer implements ApplicationContextAware {

    def applicationContext

    void setApplicationContext(ApplicationContext context) {
        this.applicationContext = context
    }


    def transform(String methodName, args) {
        println "Getting method: ${methodName}"
        Transform transform = applicationContext.getBean(methodName)
        return transform.transform(args)
    }

}
