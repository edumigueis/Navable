import 'package:flutter/material.dart';
import 'package:navable/src/components/accessibility_checks.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/models/acc_category.dart';
import 'package:navable/src/pages/models/badge.dart';
import 'package:navable/src/util/styles.dart';

import '../components/basics/expandable_section.dart';

class ProfileView extends StatelessWidget {
  ProfileView({super.key, required this.controller});

  static const routeName = '/profile';

  final ProfileController controller;
  List<AccessibilityBadge> badges = [
    AccessibilityBadge("aaa", ""),
    AccessibilityBadge("aaaa", "aaa")
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: ListView(
        children: [
          Card(
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
                  const CircleAvatar(
                    radius: 30,
                    backgroundImage: AssetImage(
                        'assets/images/flutter_logo.png'), // Replace with your photo asset
                  ),
                  const SizedBox(width: 16),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'John Doe', // Replace with dynamic name if needed
                          style: Theme.of(context).textTheme.subtitle,
                        ),
                        Text(
                          '800 POINTS', // Replace with dynamic name if needed
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
          ),
          ExpandableSection(
              title: 'Accessibility',
              child: AccessibilityChecks(title: "a", buttons: [
                AccessibilityCategory(1, "a", "b"),
                AccessibilityCategory(2, "b", "b"),
                AccessibilityCategory(3, "c", "a")
              ])),
          ExpandableSection(
              title: 'Badges',
              child: Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 15),
                  child: Wrap(
                    spacing: 15,
                    children: badges.map((badge) {
                      return Column(
                        children: [
                          CircleAvatar(
                            radius: 30,
                            backgroundImage: AssetImage("assets/images/flutter_logo.png"), // Replace with your photo asset
                          ),
                          Text(badge.title)
                        ],
                      );
                    }).toList(),
                  ))),
        ],
      ),
    );
  }
}
