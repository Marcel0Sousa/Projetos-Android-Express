
## Android Express 
     

| Projeto App Fitmess Tracker |
| ----------------------------- |
| 1. Interface Grafica |
| 2. Validação de formulário |
| 3. Regra de negócio |
| 4. Implementação de Dialos/PopUps |
| 5. Gerenciamento do teclado com ```getSystemService()``` |
| 6. Listas dinâmicas com o RecyclerView |
| 7. Implementação do metodo de click na lista |
| 8. Dependências do Room |
| 9. Configurando o Jetpack Room |

### Dependências do Room
```
plugins {
    id("kotlin-kapt")
}

val val room_version = "2.6.1"
dependencies {
    implementation ("androidx.room:room-runtime:room_version")
    kapt ("androidx.room:room-compiler:room_version")
}
```