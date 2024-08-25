import 'package:flutter/material.dart';

import '../settings/settings_view.dart';

class TopBar extends StatelessWidget implements PreferredSizeWidget {
  const TopBar({super.key});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Colors.transparent, // Transparent background
      elevation: 0, // Remove shadow
      leading: IconButton(
        icon: const Icon(Icons.menu), // Replace with your desired icon
        onPressed: () {
          // Define the action for the icon button, e.g., open a drawer
        },
      ),
      title: Center(
        child: TextButton.icon(
          label: const Text('Center Button'),
          icon: const Icon(Icons.star), // Replace with your desired icon
          onPressed: () {
            // Define the action for the center button
          },
          style: TextButton.styleFrom(
            foregroundColor:
                Colors.white, // Adjust text and icon color if needed
          ),
        ),
      ),
      actions: [
        TextButton.icon(
          label: const Text('Filter'),
          icon: const Icon(Icons.settings),
          onPressed: () {
            Navigator.restorablePushNamed(context, SettingsView.routeName);
          },
          style: TextButton.styleFrom(
            foregroundColor:
                Colors.white, // Adjust text and icon color if needed
          ),
        ),
      ],
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}
