import 'package:flutter/material.dart';
import 'package:navable/src/pages/controllers/signup_controller.dart';
import 'package:navable/src/util/styles.dart';

import '../components/basics/navable_button.dart';
import '../components/basics/navable_text_input.dart';

class SignUpView extends StatefulWidget {
  const SignUpView({super.key, required this.controller});

  static const routeName = '/signup';

  final SignupController controller;

  @override
  State<SignUpView> createState() => _SignUpViewState();
}

class _SignUpViewState extends State<SignUpView> {
  // Controllers for each text field
  final _nameController = TextEditingController();
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _confirmPasswordController = TextEditingController();

  @override
  void dispose() {
    // Dispose controllers to avoid memory leaks
    _nameController.dispose();
    _emailController.dispose();
    _passwordController.dispose();
    _confirmPasswordController.dispose();
    super.dispose();
  }

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
                ),
              ],
            ),
            Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 12.0, 15.0, 20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  NavableTextInput(
                    "Nome",
                    controller: _nameController,
                    hintText: "Qual seu nome?",
                  ),
                  const SizedBox(height: 20),
                  NavableTextInput(
                    "E-mail",
                    controller: _emailController,
                    hintText: "Digite seu e-mail",
                  ),
                  const SizedBox(height: 20),
                  NavableTextInput(
                    "Senha",
                    controller: _passwordController,
                    hintText: "Digite sua senha",
                    obscureText: true,
                  ),
                  const SizedBox(height: 20),
                  NavableTextInput(
                    "Confirmar senha",
                    controller: _confirmPasswordController,
                    hintText: "Digite novamente a senha",
                    obscureText: true,
                  ),
                ],
              ),
            ),
            Padding(
              padding:
              const EdgeInsets.symmetric(horizontal: 20.0, vertical: 20.0),
              child: NavableButton(
                "REGISTRAR",
                onPressed: () async {
                  await widget.controller.register(
                    name: _nameController.text,
                    email: _emailController.text,
                    password: _passwordController.text,
                    confirmPassword: _confirmPasswordController.text,
                  );
                  if (widget.controller.errorMsg != null) {
                    setState(() {});
                  } else {
                    Navigator.pushNamed(context, "/pickacc");
                  }
                },
              ),
            ),
            if (widget.controller.errorMsg != null)
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 5),
                child: Text(
                  widget.controller.errorMsg!,
                  style: Theme.of(context).textTheme.error
                ),
              ),
          ],
        ),
      ),
    );
  }
}
