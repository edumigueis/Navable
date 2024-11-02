import 'package:flutter/material.dart';
import 'package:navable/src/components/basics/navable_text_input.dart';
import 'package:navable/src/pages/controllers/signin_controller.dart';
import 'package:navable/src/util/styles.dart';

import '../components/basics/navable_button.dart';

class SignInView extends StatefulWidget {
  const SignInView({super.key, required this.controller});

  static const routeName = '/signin';

  final SigninController controller;

  @override
  _SignInViewState createState() => _SignInViewState();
}

class _SignInViewState extends State<SignInView> {
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  void dispose() {
    _emailController.dispose();
    _passwordController.dispose();
    super.dispose();
  }

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
                  padding: const EdgeInsets.all(20.0),
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
                controller: _emailController,
                hintText: "Digite seu e-mail",
              ),
            ),
            Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 12.0, 15.0, 20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  NavableTextInput(
                    "Senha",
                    controller: _passwordController,
                    hintText: "Digite sua senha",
                    obscureText: true,
                  ),
                  Container(
                    margin: const EdgeInsets.only(top: 10.0),
                    alignment: Alignment(1, 0),
                    child: Text(
                      "Forgot your password?",
                      style: Theme.of(context).textTheme.caption,
                      textAlign: TextAlign.right,
                    ),
                  ),
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 20.0, 15.0, 20.0),
              child: NavableButton(
                "ENTRAR",
                onPressed: () async {
                  try {
                    await widget.controller.signin(
                      _emailController.text,
                      _passwordController.text,
                    );
                    Navigator.pushNamed(context, "/home");
                  } catch (e) {
                    print(e);
                    setState(() {});
                  }
                },
              ),
            ),
            // Error message display
            if (widget.controller.errorMsg != null)
              Padding(
                padding: EdgeInsets.symmetric(horizontal: 15.0, vertical: 5),
                child: Text(
                  widget.controller.errorMsg!,
                  style: Theme.of(context).textTheme.error,
                ),
              ),
          ],
        ),
      ),
    );
  }
}
