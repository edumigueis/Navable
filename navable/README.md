# Guia para Rodar a Aplicação Flutter

Esta aplicação tem como objetivo apoiar pessoas com deficiência e/ou necessidades especiais, facilitando a locomoção pela cidade e a localização de estabelecimentos acessíveis. Ela permite que os usuários consultem e avaliem espaços públicos e privados, com foco em acessibilidade. O aplicativo se comunica com uma API em Java que interage com o banco de dados, armazenando informações sobre usuários, estabelecimentos, ocorrências e avaliações. Note que nem todas as funcionalidades foram completamente desenvolvidas, a exemplo das avaliações que estão apenas prototipadas.

## Pré-requisitos

Antes de rodar a aplicação Flutter, você precisa ter os seguintes itens instalados:

- **Flutter**: [Instalar Flutter](https://flutter.dev/docs/get-started/install)
- **Android Studio ou IntelliJ** (ou outro editor de sua preferência): [Instalar Android Studio](https://developer.android.com/studio)
- **Java Development Kit (JDK)** 21: [Baixar JDK 21](https://jdk.java.net/21/)
- **Git**: [Instalar Git](https://git-scm.com/)

## Configurando o Ambiente

0. **Siga o guia oficial de instalação do Flutter**
   [Guia](https://docs.flutter.dev/get-started/install)

2. **Verificar Instalação do Flutter**:

   Para verificar se o Flutter está corretamente instalado, execute o seguinte comando:
```bash
flutter doctor
```

   Esse comando verifica o ambiente e pode sugerir correções caso algum pré-requisito esteja faltando.

2. **Configuração do Android Emulator** (se for testar no Android):

   Se for usar o emulador, abra o Android Studio ou o IntelliJ e crie um novo dispositivo virtual (emulador). Recomendamos rodar mobile ou a versão web - note que a versão linux ou windows não possuem suporte a geolocalização.

## Executando a Aplicação

1. **Rodar no Emulador ou Dispositivo**:

   Para rodar a aplicação, execute o seguinte comando:
```bash
flutter run
```
   O Flutter irá compilar a aplicação e iniciar o aplicativo no emulador ou no dispositivo conectado.
