// occurrence_card.dart
import 'package:flutter/material.dart';
import 'package:navable/src/pages/models/warning.dart';

class WarningCard extends StatelessWidget {
  final Warning warning;
  final VoidCallback onClose;
  final IconData icon;
  final Color iconColor;
  final int upvotes; // You might want to get this from your warning model
  final VoidCallback onUpvote; // Callback for handling upvotes

  const WarningCard({
    super.key,
    required this.warning,
    required this.onClose,
    this.icon = Icons.warning_rounded,
    this.iconColor = Colors.orange,
    this.upvotes = 0, // Default value if not provided
    this.onUpvote = _defaultOnUpvote,
  });

  static void _defaultOnUpvote() {
    // Default empty implementation
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: const EdgeInsets.all(15.0),
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
        child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // Left side - Image/Icon
                Container(
                  width: 80,
                  height: 80,
                  decoration: BoxDecoration(
                    color: iconColor.withOpacity(0.1),
                    borderRadius: BorderRadius.circular(8),
                  ),
                  child: Center(
                    child: Icon(icon, color: iconColor, size: 40),
                  ),
                ),

                const SizedBox(width: 16),

                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      // Top row with title and close button
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Expanded(
                            child: Text(
                              warning.tipoOcorrencia.nome,
                              style: const TextStyle(
                                fontSize: 18.0,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                          IconButton(
                            padding: EdgeInsets.zero,
                            constraints: const BoxConstraints(),
                            icon: const Icon(Icons.close, size: 20),
                            onPressed: onClose,
                          ),
                        ],
                      ),

                      const SizedBox(height: 4),

                      // Reported date
                      Text(
                        'Reported: ',
                        style:
                            const TextStyle(fontSize: 14.0, color: Colors.grey),
                      ),

                      const SizedBox(height: 4),

                      // Description
                      Text(
                        "No description available",
                        style: const TextStyle(fontSize: 14.0),
                        maxLines: 2,
                        overflow: TextOverflow.ellipsis,
                      ),

                      const SizedBox(height: 8),

                      // Upvote row
                      Row(
                        children: [
                          InkWell(
                            onTap: onUpvote,
                            child: const Icon(
                              Icons.thumb_up,
                              size: 18,
                              color: Colors.blue,
                            ),
                          ),
                          const SizedBox(width: 4),
                          Text(
                            '$upvotes',
                            style: const TextStyle(
                              fontSize: 14,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ],
            )));
  }
}
