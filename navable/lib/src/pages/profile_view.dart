import 'package:flutter/material.dart';
import 'package:navable/src/components/accessibility_checks.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/util/styles.dart';

import '../components/basics/expandable_section.dart';

class ProfileView extends StatelessWidget {
  const ProfileView({super.key, required this.controller});

  static const routeName = '/profile';

  final ProfileController controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: FutureBuilder<void>(
        future: controller.fetchLoggedUser(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            print(snapshot.error);
            return const Center(child: Text("Failed to load user profile"));
          } else {
            return ListView(
              children: [
                _buildProfileCard(context),
                _buildAccessibilitySection(),
                _buildBadgesSection(),
              ],
            );
          }
        },
      ),
    );
  }

  Widget _buildProfileCard(BuildContext context) {
    return Card(
      elevation: 0,
      margin: const EdgeInsets.fromLTRB(15, 0, 15, 15),
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.all(Radius.circular(10)),
      ),
      color: NavableColors.gray,
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Row(
          children: [
            CircleAvatar(
              radius: 30,
              backgroundColor: Colors.white,
              child: ClipOval(
                child: Image.network(
                  "", // TO DO ADD USER PROF PIC
                  width: 60,
                  height: 60,
                  fit: BoxFit.cover,
                  errorBuilder: (context, error, stackTrace) {
                    return const Icon(Icons.person, size: 30, color: Colors.grey);
                  },
                ),
              ),
            ),
            const SizedBox(width: 16),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    controller.user.name,
                    style: Theme.of(context).textTheme.subtitle,
                  ),
                  Text(
                    '${controller.user.points} POINTS',
                    style: Theme.of(context).textTheme.highlight,
                  ),
                  const SizedBox(height: 10),
                  const LinearProgressIndicator(
                    value: 0.7,
                    backgroundColor: NavableColors.gray,
                    color: NavableColors.blueAccent,
                  ),
                ],
              ),
            ),
            const SizedBox(width: 16),
            IconButton(
              icon: const Icon(Icons.settings),
              onPressed: () {
                Navigator.restorablePushNamed(context, "/settings");
              },
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildAccessibilitySection() {
    return ExpandableSection(
      title: 'Accessibility',
      child: FutureBuilder<void>(
        future: controller.fetchUserCategories(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            print(snapshot.error);
            return const Center(
                child: Text("Error loading accessibility categories"));
          } else if (controller.categories.isEmpty) {
            return const Center(
                child: Text("Você ainda não selecionou nenhuma categoria."));
          } else {
            return Center(
                child: SizedBox(
                    height: 200.0,
                    child: AccessibilityChecks(
                      title: "",
                      buttons: controller.categories,
                      onSelectionChanged: (selectedCategories) {
                        WidgetsBinding.instance.addPostFrameCallback((_) {
                          if (selectedCategories != controller.categories) {
                            controller.categories = selectedCategories;
                          }
                        });
                      },
                    )));
          }
        },
      ),
    );
  }

  Widget _buildBadgesSection() {
    return ExpandableSection(
      title: 'Badges',
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 15),
        child: FutureBuilder<void>(
          future: controller.fetchUserAccessibilityBadges(),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return const Center(child: CircularProgressIndicator());
            } else if (snapshot.hasError) {
              print(snapshot.error);
              return const Center(child: Text("Error loading badges"));
            } else if (controller.badges.isEmpty) {
              return const Center(
                  child: Text("Você ainda não ganhou nenhum selo."));
            } else {
              return Wrap(
                spacing: 15,
                children: controller.badges.map((badge) {
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      const CircleAvatar(
                        radius: 30,
                        backgroundImage:
                            AssetImage("assets/images/flutter_logo.png"),
                      ),
                      Text(badge.title),
                    ],
                  );
                }).toList(),
              );
            }
          },
        ),
      ),
    );
  }

}
