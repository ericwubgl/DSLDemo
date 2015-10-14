import demo.AddTransformer
import demo.ConcatTransformer
import demo.DataLoader
import demo.Field
import demo.Transformer

// Place your Spring DSL code here
beans = {

    importBeans('classpath:/spring/resources.xml')

    add(AddTransformer)

    concat(ConcatTransformer)

    dataLoader(DataLoader) { bean ->
        bean.scope = 'prototype'
        dataImport = ref('dataImport')
    }

    transformer(Transformer)

    field(Field) { bean ->
        bean.scope = 'prototype'
        transformer = ref('transformer')
    }

}
