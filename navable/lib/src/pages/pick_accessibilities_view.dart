import 'package:flutter/material.dart';
import '../components/accessibility_checks.dart';
import '../components/basics/navable_button.dart';
import '../util/styles.dart';
import 'controllers/pick_accessibilities_controller.dart';

class PickAccessibilitiesView extends StatelessWidget {
  const PickAccessibilitiesView({super.key, required this.controller});

  static const routeName = '/pickacc';

  final PickAccessibilitiesController controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      extendBody: true,
      backgroundColor: Colors.white,
      body: Center(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Padding(
              padding: const EdgeInsets.fromLTRB(15.0, 4.0, 0.0, 15.0),
              child: Text(
                "Quais as suas necessidades?",
                style: Theme.of(context).textTheme.smalltitle,
              ),
            ),
            FutureBuilder<void>(
              future: controller.fetchCategories(),
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(child: CircularProgressIndicator());
                }
                if (snapshot.hasError) {
                  print(snapshot.error);
                  return const Center(child: Text("Erro ao carregar categorias"));
                }
                return Expanded(
                  child: AccessibilityChecks(
                    title: "",
                    buttons: controller.categories,
                    onSelectionChanged: (selectedCategories) {
                      controller.categories = selectedCategories;
                    },
                  ),
                );
              },
            ),
            if (controller.errorMessage != null) // Display error message
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20.0),
                child: Text(
                  controller.errorMessage!,
                  style: TextStyle(color: Colors.red),
                ),
              ),
            Padding(
              padding: const EdgeInsets.fromLTRB(20.0, 0, 20.0, 20.0),
              child: NavableButton(
                "NEXT",
                onPressed: () {
                  controller.registerCategories();
                  if (controller.errorMessage == null) {
                    Navigator.pushNamed(context, "/home");
                  }
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}