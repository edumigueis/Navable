import 'package:flutter/material.dart';
import '../components/navable_button.dart';
import '../util/styles.dart';
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
                padding: EdgeInsets.fromLTRB(15.0, 50.0, 15.0, 20.0),
                child: Text(
                  "Login",
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
              padding: const EdgeInsets.fromLTRB(15.0, 20.0, 15.0, 20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Container(
                    margin: const EdgeInsets.only(bottom: 10.0),
                    child: const Text(
                      "E-mail:",
                      style: TextStyle(
                          fontSize: 19.0,
                          fontFamily: 'Ubuntu',
                          fontWeight: FontWeight.w500,
                          color: Color.fromRGBO(40, 40, 40, 1)),
                      textAlign: TextAlign.start,
                    ),
                  ),
                  TextField(
                    onChanged: (val) => {},
                    decoration: const InputDecoration(
                        focusedBorder: OutlineInputBorder(
                            borderSide: BorderSide(
                                color: Color.fromRGBO(30, 30, 30, 1),
                                width: 4.0),
                            borderRadius: BorderRadius.zero),
                        enabledBorder: OutlineInputBorder(
                            borderSide: BorderSide(
                                color: Color.fromRGBO(30, 30, 30, 1),
                                width: 3.0),
                            borderRadius: BorderRadius.zero),
                        hintText: 'Enter your email'),
                  ),
                ],
              )),
          Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 12.0, 15.0, 20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Container(
                    margin: const EdgeInsets.only(bottom: 10.0),
                    child: const Text(
                      "Password:",
                      style: TextStyle(
                          fontSize: 19.0,
                          fontFamily: 'Ubuntu',
                          fontWeight: FontWeight.w500,
                          color: Color.fromRGBO(40, 40, 40, 1)),
                      textAlign: TextAlign.start,
                    ),
                  ),
                  TextField(
                      onChanged: (val) => {},
                      decoration: const InputDecoration(
                          focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(
                                  color: Color.fromRGBO(30, 30, 30, 1),
                                  width: 4.0),
                              borderRadius: BorderRadius.zero),
                          enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(
                                  color: Color.fromRGBO(30, 30, 30, 1),
                                  width: 3.0),
                              borderRadius: BorderRadius.zero),
                          hintText: 'Enter your password'),
                      obscureText: true),
                  Container(
                    margin: const EdgeInsets.only(top: 10.0),
                    alignment: Alignment(1, 0),
                    child: const Text("Forgot your password?",
                        style: TextStyle(
                            fontSize: 14.0,
                            fontFamily: 'Ubuntu',
                            fontWeight: FontWeight.w600,
                            color: Color.fromRGBO(40, 40, 40, 1)),
                        textAlign: TextAlign.right),
                  )
                ],
              )),
          Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 20.0, 15.0, 20.0),
              child: NavableButton(
                "SIGN UP",
                onPressed: () {
                  Navigator.pushNamed(context, "/pickacc");
                },
              )),
          const Padding(
            padding: EdgeInsets.symmetric(horizontal: 15.0, vertical: 5),
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
