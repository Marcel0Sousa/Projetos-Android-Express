
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
| 10. Inserindo dados no banco |
| 11. Listando dados do banco |
| 12. Formatando o resultado da lista |
| 13. Implementando um ```menu``` de acesso aos registros salvos |

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

## Screenshot
![](https://github.com/Marcel0Sousa/Projetos-Android-Express/blob/FitnessTracker/FitnessTracker.gif?raw=true)