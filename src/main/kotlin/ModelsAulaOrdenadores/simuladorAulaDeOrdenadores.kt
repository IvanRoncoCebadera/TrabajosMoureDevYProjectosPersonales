package ModelsAulaOrdenadores

fun main(){
    println("Introduce la indentificacion del aula:")
    val identificacion = readln().trim()
    println("Introduce el curso que se está cursando en el aula:")
    val curso = readln().trim()

    var aula = AulaOrdenadores(identificacion, curso)

    var opcion = 0
    do{
        println("Introduce la opción que deseas")
        opcion = menu()
        when(opcion){
            1 -> aula.mostrarTodosLosOrdenadores()
            2 -> aula.mostrarUnOrdenadorPorId()
            3 -> aula.añadirOrdenadorAlAula()
            4 -> aula.sacarOrdenadorDelAula()
            5 -> aula.actualizarOrdenadorDelAula()
        }
    }while(opcion != 0)
    println("Adios..")
}

fun menu(): Int {
    var opcion = 0
    do{
        println("[1] Mostrar todos los ordenadores")
        println("[2] Mostrar un ordenador")
        println("[3] Añadir un ordenador")
        println("[4] Sacar un ordenador")
        println("[5] Actualizar un ordenador")
        println("[0] Salir")
        opcion = readln().toInt()
        if(opcion !in (0..5)){
            println("La opcion introducida es incorrecta, vuelva a probar:")
            opcion = -1
        }
    }while(opcion == -1)
    return opcion
}
