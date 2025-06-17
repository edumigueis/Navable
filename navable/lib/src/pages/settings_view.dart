import 'package:flutter/material.dart';

import 'controllers/settings_controller.dart';

class SettingsView extends StatelessWidget {
  const SettingsView({super.key, required this.controller});

  static const routeName = '/settings';
  final SettingsController controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Settings'),
        elevation: 2,
      ),
      body: ListView(
        children: [
          _buildSectionHeader(context, 'Appearance'),
          _buildThemeSelector(context),
          _buildSwitchOption(
            context: context,
            title: 'Use system font size',
            subtitle: 'Follow system accessibility settings',
            value: true,
            onChanged: (value) {
              // Update font size setting
            },
          ),
          _buildDivider(),

          _buildSectionHeader(context, 'Notifications'),
          _buildSwitchOption(
            context: context,
            title: 'Push notifications',
            subtitle: 'Receive alerts when new content is available',
            value: true,
            onChanged: (value) {
              // Update push notification setting
            },
          ),
          _buildSwitchOption(
            context: context,
            title: 'Email notifications',
            subtitle: 'Receive weekly digest of new content',
            value: false,
            onChanged: (value) {
              // Update email notification setting
            },
          ),
          _buildDivider(),

          _buildSectionHeader(context, 'Privacy'),
          _buildSwitchOption(
            context: context,
            title: 'Analytics',
            subtitle: 'Help us improve by sending anonymous usage data',
            value: true,
            onChanged: (value) {
              // Update analytics setting
            },
          ),
          _buildNavigationOption(
            context: context,
            title: 'Data & Privacy',
            subtitle: 'Manage your data and privacy options',
            onTap: () {
              // Navigate to privacy settings
            },
          ),
          _buildDivider(),

          _buildSectionHeader(context, 'Content'),
          _buildNavigationOption(
            context: context,
            title: 'Language',
            subtitle: 'English (US)',
            onTap: () {
              // Navigate to language settings
            },
          ),
          _buildNavigationOption(
            context: context,
            title: 'Content preferences',
            subtitle: 'Customize what content you see',
            onTap: () {
              // Navigate to content preferences
            },
          ),
          _buildDivider(),

          _buildSectionHeader(context, 'About'),
          _buildNavigationOption(
            context: context,
            title: 'App version',
            subtitle: '1.0.0',
            onTap: () {
              // Show version details or check for updates
            },
          ),
          _buildNavigationOption(
            context: context,
            title: 'Terms of Service',
            onTap: () {
              // Navigate to terms of service
            },
          ),
          _buildNavigationOption(
            context: context,
            title: 'Privacy Policy',
            onTap: () {
              // Navigate to privacy policy
            },
          ),
          _buildDivider(),

          // Account management options
          Padding(
            padding:
                const EdgeInsets.symmetric(horizontal: 16.0, vertical: 8.0),
            child: ElevatedButton(
              onPressed: () {
                _showLogoutConfirmation(context);
              },
              style: ElevatedButton.styleFrom(
                backgroundColor: Theme.of(context).primaryColor,
                foregroundColor: Colors.white,
                padding: const EdgeInsets.symmetric(vertical: 12),
              ),
              child: const Text('Log Out'),
            ),
          ),
          Padding(
            padding:
                const EdgeInsets.symmetric(horizontal: 16.0, vertical: 8.0),
            child: TextButton(
              onPressed: () {
                _showDeleteAccountConfirmation(context);
              },
              style: TextButton.styleFrom(
                foregroundColor: Colors.red,
                padding: const EdgeInsets.symmetric(vertical: 12),
              ),
              child: const Text('Delete Account'),
            ),
          ),
          const SizedBox(height: 24),
        ],
      ),
    );
  }

  Widget _buildSectionHeader(BuildContext context, String title) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(16.0, 24.0, 16.0, 8.0),
      child: Text(
        title,
        style: TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.bold,
          color: Theme.of(context).colorScheme.primary,
          letterSpacing: 0.5,
        ),
      ),
    );
  }

  Widget _buildThemeSelector(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'Theme Mode',
            style: TextStyle(fontSize: 16),
          ),
          const SizedBox(height: 8),
          Container(
            padding:
                const EdgeInsets.symmetric(horizontal: 12.0, vertical: 4.0),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(8),
              border: Border.all(color: Theme.of(context).dividerColor),
            ),
            child: DropdownButtonHideUnderline(
              child: DropdownButton<ThemeMode>(
                value: controller.themeMode,
                onChanged: controller.updateThemeMode,
                isExpanded: true,
                items: const [
                  DropdownMenuItem(
                    value: ThemeMode.system,
                    child: Text('System Theme'),
                  ),
                  DropdownMenuItem(
                    value: ThemeMode.light,
                    child: Text('Light Theme'),
                  ),
                  DropdownMenuItem(
                    value: ThemeMode.dark,
                    child: Text('Dark Theme'),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildSwitchOption({
    required BuildContext context,
    required String title,
    String? subtitle,
    required bool value,
    required ValueChanged<bool> onChanged,
  }) {
    return ListTile(
      title: Text(title),
      subtitle: subtitle != null ? Text(subtitle) : null,
      trailing: Switch.adaptive(
        value: value,
        onChanged: onChanged,
        activeColor: Theme.of(context).colorScheme.primary,
      ),
    );
  }

  Widget _buildNavigationOption({
    required BuildContext context,
    required String title,
    String? subtitle,
    required VoidCallback onTap,
  }) {
    return ListTile(
      title: Text(title),
      subtitle: subtitle != null ? Text(subtitle) : null,
      trailing: const Icon(Icons.chevron_right),
      onTap: onTap,
    );
  }

  Widget _buildDivider() {
    return const Divider(height: 1, indent: 16, endIndent: 16);
  }

  void _showLogoutConfirmation(BuildContext context) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Log Out'),
          content: const Text('Are you sure you want to log out?'),
          actions: <Widget>[
            TextButton(
              onPressed: () => Navigator.of(context).pop(),
              child: const Text('Cancel'),
            ),
            TextButton(
              onPressed: () {
                controller.logout().then((_) {
                  Navigator.pushNamedAndRemoveUntil(context, "/", (route) => false);
                }).catchError((error) {
                  // Handle any errors that occur during account deletion
                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(
                      content: Text('Error logging out: $error'),
                      backgroundColor: Colors.red,
                    ),
                  );
                });
              },
              child: const Text('Log Out'),
            ),
          ],
        );
      },
    );
  }

  void _showDeleteAccountConfirmation(BuildContext context) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Delete Account'),
          content: const Text(
            'This action cannot be undone. All your data will be permanently deleted. Are you sure you want to continue?',
          ),
          actions: <Widget>[
            TextButton(
              onPressed: () => Navigator.of(context).pop(),
              child: const Text('Cancel'),
            ),
            TextButton(
              style: TextButton.styleFrom(
                foregroundColor: Colors.red,
              ),
              onPressed: () {
                controller.deleteAccount().then((_) {
                  Navigator.pushNamedAndRemoveUntil(
                      context, "/", (route) => false);
                }).catchError((error) {
                  // Handle any errors that occur during account deletion
                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(
                      content: Text('Error deleting account: $error'),
                      backgroundColor: Colors.red,
                    ),
                  );
                });
              },
              child: const Text('Delete'),
            ),
          ],
        );
      },
    );
  }
}
