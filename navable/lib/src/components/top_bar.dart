import 'package:flutter/material.dart';
import 'package:navable/src/components/place_card.dart';
import 'package:navable/src/pages/profile_view.dart';


class TopBar extends StatelessWidget implements PreferredSizeWidget {
  final VoidCallback onTapFilter;
  final VoidCallback onTapSearch;

  const TopBar(
      {super.key, required this.onTapFilter, required this.onTapSearch});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Colors.transparent,
      elevation: 0,
      leading: IconButton(
        icon: const Icon(Icons.person_outline),
        onPressed: () {
          Navigator.restorablePushNamed(context, ProfileView.routeName);
        },
        style: TextButton.styleFrom(
          foregroundColor: const Color(0xff191919),
        ),
      ),
      title: Center(
        child: TextButton.icon(
          label: const Text('Search'),
          icon: const Icon(Icons.search),
          onPressed: () {
            onTapSearch();
          },
          style: TextButton.styleFrom(
              foregroundColor: const Color(0xff191919),
              backgroundColor: Colors.white),
        ),
      ),
      actions: [
        IconButton(
          icon: const Icon(Icons.filter_alt_outlined),
          onPressed: () {
            onTapFilter();
          },
          style: TextButton.styleFrom(
            foregroundColor: const Color(0xff191919),
          ),
        ),
      ],
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}
