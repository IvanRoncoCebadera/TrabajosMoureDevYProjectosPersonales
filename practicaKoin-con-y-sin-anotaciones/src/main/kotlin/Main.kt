import controller.Controller
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule

fun main(args: Array<String>){
    startKoin{
        //slf4jLogger()
        //modules(myModule)
        defaultModule()
    }

    val controller = Controller()
    controller.viewmodel

    println("En este ejemplo podemos apreciar como usar koin con y sin anotaciones a la perfección")
}