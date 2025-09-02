# Sistema de Matricula
O projeto tem como objetivo informatizar um sistema de matrículas de uma universidade. A secretaria e professores devem possuir a capacidade de gerenciar a matrícula de seus alunos, seguindo as regras de negócio propostas pela atividade.

## Integrantes
* Arthur Luiz Alves Soares
* Igor Emanuel Andrade Reis
* Túlio Henrique Martins Gonçalves

## Orientadores
* João Pedro Oliveira Batisteli

---

## Instruções de utilização
Para rodar o sistema, compile todos os arquivos Java e em seguida execute a classe App. A forma mais simples é usar o terminal na raiz do projeto:

```bash
# compilar
javac -d out $(find src -name "*.java")

# executar
cd out
java App
```
O sistema cria automaticamente um usuário de secretaria padrão (login: admin, senha: admin). A partir dele você pode cadastrar cursos, disciplinas, alunos e professores. Todos os dados são persistidos em arquivo (database.dat), sendo carregados novamente a cada execução.