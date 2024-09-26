import 'package:flutter/material.dart';
import 'package:navable/src/components/basics/navable_text_input.dart';
import 'package:navable/src/util/styles.dart';

import '../components/basics/navable_button.dart';
import 'controllers/settings_controller.dart';

class SignInView extends StatelessWidget {
  const SignInView({super.key, required this.controller});

  static const routeName = '/signin';

  final SettingsController controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      extendBodyBehindAppBar: true,
      extendBody: true,
      backgroundColor: Colors.white,
      body: Center(
          child: ListView(
        children: <Widget>[
          Row(
            children: <Widget>[
              Padding(
                padding: EdgeInsets.all(20.0),
                child: Text(
                  "Login",
                  style: Theme.of(context).textTheme.heading,
                ),
              ),
            ],
          ),
          Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 20.0, 15.0, 20.0),
              child: NavableTextInput(
                "E-mail",
                controller: TextEditingController(),
                hintText: "Digite seu e-mail",
              )),
          Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 12.0, 15.0, 20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  NavableTextInput(
                    "Senha",
                    controller: TextEditingController(),
                    hintText: "Digite sua senha",
                  ),
                  Container(
                    margin: const EdgeInsets.only(top: 10.0),
                    alignment: Alignment(1, 0),
                    child: Text("Forgot your password?",
                        style: Theme.of(context).textTheme.caption,
                        textAlign: TextAlign.right),
                  )
                ],
              )),
          Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 20.0, 15.0, 20.0),
              child: NavableButton(
                "SIGN IN",
                onPressed: () {
                  Navigator.pushNamed(context, "/home");
                },
              )),
          Padding(
              padding: EdgeInsets.symmetric(horizontal: 15.0, vertical: 5),
              child: Text("Opa deu erro",
                  style: Theme.of(context).textTheme.error))
        ],
      )),
    );
  }
}
