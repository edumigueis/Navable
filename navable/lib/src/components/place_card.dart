import 'package:flutter/material.dart';

class PlaceCard extends StatelessWidget {
  const PlaceCard({
    super.key,
    required this.title,
    required this.icon,
    required this.iconColor,
    required this.text,
    required this.onClose
  });

  final String title;
  final IconData icon;
  final Color iconColor;
  final String text;
  final VoidCallback onClose;

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 200,
      margin: EdgeInsets.symmetric(
        horizontal: MediaQuery.of(context).size.width * 0.1,
      ),
      padding: EdgeInsets.all(16.0),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(16.0),
        boxShadow: const [
          BoxShadow(
            color: Colors.black26,
            blurRadius: 10.0,
            offset: Offset(0, -4),
          ),
        ],
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(children: [
            IconButton(
              icon: Icon(Icons.close),
              onPressed: onClose, // Close the modal when "X" is clicked
            ),
            Text(
            title,
            style: Theme.of(context).textTheme.headlineMedium?.copyWith(
              fontWeight: FontWeight.bold,
            ),
          ),]),
          const SizedBox(height: 16), // Espaço entre o título e o conteúdo abaixo
          Row(
            children: [
              Icon(
                icon,
                color: iconColor,
                size: 24.0, // Tamanho do ícone
              ),
              const SizedBox(width: 8), // Espaço entre o ícone e o texto
              Expanded(
                child: Text(
                  text,
                  style: Theme.of(context).textTheme.bodyMedium,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
