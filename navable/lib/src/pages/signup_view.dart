import 'package:flutter/material.dart';
import 'package:navable/src/util/styles.dart';

import '../components/basics/navable_button.dart';
import '../components/basics/navable_text_input.dart';
import 'controllers/settings_controller.dart';

class SignUpView extends StatelessWidget {
  const SignUpView({super.key, required this.controller});

  static const routeName = '/signup';

  final SettingsController controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      extendBody: true,
      extendBodyBehindAppBar: true,
      backgroundColor: Colors.white,
      body: Center(
          child: ListView(
        children: <Widget>[
          Row(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.fromLTRB(15.0, 20.0, 15.0, 20.0),
                child: Text("Criar conta",
                    style: Theme.of(context).textTheme.heading),
              )
            ],
          ),
          Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 12.0, 15.0, 20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  NavableTextInput(
                    "E-mail",
                    controller: TextEditingController(),
                    hintText: "Digite seu e-mail",
                  ),
                  SizedBox(height: 20),
                  NavableTextInput(
                    "Senha",
                    controller: TextEditingController(),
                    hintText: "Digite sua senha",
                  ),
                  SizedBox(height: 20),
                  NavableTextInput(
                    "Confirmar senha",
                    controller: TextEditingController(),
                    hintText: "Digite novamente a senha",
                  )
                ],
              )),
          Padding(
              padding:
                  const EdgeInsets.symmetric(horizontal: 20.0, vertical: 20.0),
              child: NavableButton(
                "REGISTRAR",
                onPressed: () {
                  Navigator.pushNamed(context, "/pickacc");
                },
              )),
          Padding(
            padding: EdgeInsets.symmetric(horizontal: 20.0, vertical: 5),
            child:
                Text("Opa deu erro", style: Theme.of(context).textTheme.error),
          )
        ],
      )),
    );
  }
}
