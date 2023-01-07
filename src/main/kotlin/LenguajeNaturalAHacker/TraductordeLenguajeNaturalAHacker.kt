package LenguajeNaturalAHacker

val abecedario = "abcdefghijlnopqrstuxyz,.;:-_"

val abecedarioHacker = "48[)3f&#!17^0?925+vxjs,.;:-_"

fun main(){
    println("Introduce el texto que quieres traducir, sin tildes porfa:")
    val texto = readln().trim().lowercase()
    println("El mensaje traducido queda:")
    println(traducirAHacker(texto))

}

fun traducirAHacker(texto: String): String{
    var mensaje = StringBuilder()
    for(i in texto){
        if( i != 'k' && i != 'm' && i != 'v' && i != 'w') {
            val posicion = encontrarPosicionLetraEnAbecedario(i)
            if(posicion != -1) {
                mensaje.append(abecedarioHacker[posicion])
            }else{
                mensaje.append(" ")
            }
        }else{
            when(i){
                'k' -> mensaje.append("|<")
                'm' -> mensaje.append("|V|")
                'v' -> mensaje.append("|/")
                'w' -> mensaje.append("VV")
            }
        }
    }
    return mensaje.toString()
}

fun encontrarPosicionLetraEnAbecedario(letra: Char): Int{
    var posicion = -1
    for(i in abecedario.indices){
        if(abecedario[i] == letra){
            posicion = i
            break
        }
    }
    return posicion
}