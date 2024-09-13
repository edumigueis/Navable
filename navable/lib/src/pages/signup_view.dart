import 'package:flutter/material.dart';

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
          const Row(
            children: <Widget>[
              Padding(
                padding: EdgeInsets.fromLTRB(15.0, 20.0, 15.0, 20.0),
                child: Text(
                  "Sign up",
                  style: TextStyle(
                      fontSize: 35.0,
                      fontFamily: 'Ubuntu',
                      fontWeight: FontWeight.bold,
                      color: Color.fromRGBO(40, 40, 40, 1)),
                ),
              ),
            ],
          ),
          Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 12.0, 15.0, 20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  NavableTextInput("E-mail", controller: TextEditingController(), hintText: "Digite seu e-mail",),
                  SizedBox(height: 20),
                  NavableTextInput("Senha", controller: TextEditingController(), hintText: "Digite sua senha",),
                  SizedBox(height: 20),
                  NavableTextInput("Confirmar senha", controller: TextEditingController(), hintText: "Digite novamente a senha",)
                ],
              )),
          Padding(
              padding:
                  const EdgeInsets.symmetric(horizontal: 20.0, vertical: 20.0),
              child: NavableButton(
                "SIGN UP",
                onPressed: () {
                  Navigator.pushNamed(context, "/pickacc");
                },
              )),
          const Padding(
            padding: EdgeInsets.symmetric(horizontal: 20.0, vertical: 5),
            child: Text("Opa deu erro",
                style: TextStyle(
                    fontSize: 16,
                    fontFamily: 'Ubuntu',
                    fontWeight: FontWeight.w500,
                    color: Color.fromRGBO(237, 67, 35, 1))),
          )
        ],
      )),
    );
  }
}
