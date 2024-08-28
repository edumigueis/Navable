import 'package:flutter/material.dart';
import 'package:navable/src/components/place_card.dart';
import 'package:navable/src/pages/profile_view.dart';

import '../pages/settings_view.dart';

class TopBar extends StatelessWidget implements PreferredSizeWidget {
  final VoidCallback onTapFilter;
  final VoidCallback onTapSearch;
  const TopBar({super.key, required this.onTapFilter, required this.onTapSearch});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Colors.transparent, // Transparent background
      elevation: 0, // Remove shadow
      leading: IconButton(
        icon: const Icon(Icons.menu),
        onPressed: () {
          Navigator.restorablePushNamed(context, ProfileView.routeName);
        },
      ),
      title: Center(
        child: TextButton.icon(
          label: const Text('Search'),
          icon: const Icon(Icons.search),
          onPressed: () {
            onTapSearch();
          },
          style: TextButton.styleFrom(
            foregroundColor:
                Colors.white,
          ),
        ),
      ),
      actions: [
        TextButton.icon(
          label: const Text('Filter'),
          icon: const Icon(Icons.filter_alt),
          onPressed: () {
            onTapFilter();
          },
          style: TextButton.styleFrom(
            foregroundColor:
                Colors.white,
          ),
        ),
      ],
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}
