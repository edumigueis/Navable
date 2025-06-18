import 'package:flutter/material.dart';
import 'package:navable/src/components/place_grade_display.dart';
import 'package:navable/src/pages/models/place.dart';
import 'package:navable/src/pages/models/review.dart';
import 'package:navable/src/util/styles.dart';

// Controller for handling place card data (no changes)
class PlaceCardController with ChangeNotifier {
  final int placeId;
  bool _isLoading = true;
  String? _errorMessage;
  List<Review> _reviews = [];

  PlaceCardController(this.placeId) {
    fetchReviews();
  }

  bool get isLoading => _isLoading;
  String? get errorMessage => _errorMessage;
  List<Review> get reviews => _reviews;

  Future<void> fetchReviews() async {
    // Implementation unchanged
    _isLoading = true;
    _errorMessage = null;
    notifyListeners();

    try {
      // Simulate API call
      await Future.delayed(const Duration(seconds: 1));

      // Mock data for demonstration
      _reviews = [
        Review(
          idAvaliacao: 1,
          idUsuario: 101,
          idEstabelecimento: placeId,
          avaliacao: "Rampa de acesso bem posicionada e banheiros adaptados.",
          nota: 5,
          timestamp: DateTime.now().subtract(const Duration(days: 2)),
        ),
        Review(
          idAvaliacao: 2,
          idUsuario: 102,
          idEstabelecimento: placeId,
          avaliacao:
          "Tem rampa mas falta corrimão. Porta do banheiro estreita.",
          nota: 3,
          timestamp: DateTime.now().subtract(const Duration(days: 7)),
        ),
        Review(
          idAvaliacao: 3,
          idUsuario: 103,
          idEstabelecimento: placeId,
          avaliacao:
          "Não consegui entrar com a cadeira de rodas, degrau na entrada.",
          nota: 1,
          timestamp: DateTime.now().subtract(const Duration(days: 14)),
        ),
      ];

      _isLoading = false;
    } catch (e) {
      _errorMessage = "Falha ao carregar avaliações: ${e.toString()}";
      _isLoading = false;
    }

    notifyListeners();
  }
}

// Enhanced PlaceCard Widget
class PlaceCard extends StatefulWidget {
  const PlaceCard({
    super.key,
    required this.place,
    required this.icon,
    required this.iconColor,
    required this.onClose,
  });

  final Place place;
  final IconData icon;
  final Color iconColor;
  final VoidCallback onClose;

  @override
  State<PlaceCard> createState() => _PlaceCardState();
}

class _PlaceCardState extends State<PlaceCard> {
  late PlaceCardController _controller;

  @override
  void initState() {
    super.initState();
    // Initialize controller with place ID
    _controller =
        PlaceCardController(int.parse(widget.place.name.hashCode.toString()));
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
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
        mainAxisSize: MainAxisSize.min, // Fixed: Ensure proper column sizing
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          // Header image
          Container(
            height: 150,
            decoration: BoxDecoration(
              color: const Color(0xff77E4D4), // Fixed: Added const
              borderRadius: const BorderRadius.only( // Fixed: Added const
                topLeft: Radius.circular(16.0),
                topRight: Radius.circular(16.0),
              ),
              image: widget.place.image.isNotEmpty
                  ? DecorationImage(
                image: AssetImage("assets/${widget.place.image}"),
                fit: BoxFit.cover,
              )
                  : null,
            ),
            child: Stack(
              children: [
                // Show icon if no image
                if (widget.place.image.isEmpty)
                  Center(
                    child:
                    Icon(widget.icon, color: widget.iconColor, size: 48.0),
                  ),
                // Close button
                Positioned(
                  top: 8,
                  right: 8,
                  child: CircleAvatar(
                    backgroundColor: Colors.white,
                    radius: 16,
                    child: IconButton(
                      icon: const Icon(Icons.close, size: 16), // Fixed: Added const
                      onPressed: widget.onClose,
                      padding: EdgeInsets.zero,
                    ),
                  ),
                ),
              ],
            ),
          ),

          // Grade display
          PlaceGradeDisplay(value: widget.place.grade, stroke: 8),
          Padding(
            padding: const EdgeInsets.fromLTRB(16, 16, 16, 0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  widget.place.name,
                  style: Theme.of(context).textTheme.titleLarge?.copyWith( // Fixed: headlineSmall to titleLarge
                    fontWeight: FontWeight.bold,
                  ),
                  overflow: TextOverflow.ellipsis,
                  maxLines: 1,
                ),

                const SizedBox(height: 8), // Fixed: Added const

                // Address
                Row(
                  children: [
                    const Icon(Icons.location_on, size: 16, color: Colors.grey), // Fixed: Added const
                    const SizedBox(width: 4), // Fixed: Added const
                    Expanded(
                      child: Text(
                        widget.place.address,
                        style: Theme.of(context).textTheme.bodySmall, // Fixed: caption to bodySmall
                        overflow: TextOverflow.ellipsis,
                        maxLines: 2,
                      ),
                    ),
                  ],
                ),

                const SizedBox(height: 16), // Fixed: Added const

                // Action buttons in Google Maps style
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    _buildActionButton(
                      icon: Icons.directions,
                      label: "DIRECTIONS",
                      onTap: () => Navigator.pushNamed(
                          context, "/directions",
                          arguments: widget.place),
                    ),
                    _buildActionButton(
                      icon: Icons.star_outline,
                      label: "REVIEW",
                      onTap: () => Navigator.pushNamed(context, "/review",
                          arguments: widget.place),
                    ),
                    _buildActionButton(
                      icon: Icons.report_problem_outlined,
                      label: "REPORT",
                      onTap: () => Navigator.pushNamed(context, "/report",
                          arguments: widget.place),
                    ),
                    _buildActionButton(
                      icon: Icons.share,
                      label: "SHARE",
                      onTap: () {
                        // Implement share functionality
                      },
                    ),
                  ],
                ),
              ],
            ),
          ),

          const Divider(height: 24), // Fixed: Added const and increased height

          // Reviews section
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "Reviews",
                  style: Theme.of(context).textTheme.titleMedium?.copyWith( // Fixed: subtitle to titleMedium
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(height: 8), // Fixed: Added const

                // Reviews list with loading state
                AnimatedBuilder(
                  animation: _controller,
                  builder: (context, _) {
                    if (_controller.isLoading) {
                      return const Center( // Fixed: Added const
                        child: Padding(
                          padding: EdgeInsets.all(16.0),
                          child: CircularProgressIndicator(),
                        ),
                      );
                    } else if (_controller.errorMessage != null) {
                      return Center(
                        child: Text(
                          _controller.errorMessage!,
                          style: const TextStyle(color: Colors.red), // Fixed: Added const
                        ),
                      );
                    } else if (_controller.reviews.isEmpty) {
                      return const Center( // Fixed: Added const
                        child: Padding(
                          padding: EdgeInsets.all(16.0),
                          child: Text(
                            "No reviews yet. Be the first to review!",
                            style: TextStyle(color: Colors.grey),
                          ),
                        ),
                      );
                    } else {
                      return ListView.builder(
                        shrinkWrap: true,
                        physics: const NeverScrollableScrollPhysics(), // Fixed: Added const
                        itemCount: _controller.reviews.length,
                        itemBuilder: (context, index) {
                          final review = _controller.reviews[index];
                          return _buildReviewItem(context, review);
                        },
                      );
                    }
                  },
                ),

                // "See all reviews" button if there are reviews
                AnimatedBuilder(
                  animation: _controller,
                  builder: (context, _) {
                    if (_controller.reviews.isNotEmpty) {
                      return Center(
                        child: TextButton.icon(
                          icon: const Icon(Icons.expand_more), // Fixed: Added const
                          label: const Text("SEE ALL REVIEWS"), // Fixed: Added const
                          onPressed: () {
                            Navigator.pushNamed(context, "/all-reviews",
                                arguments: widget.place);
                          },
                        ),
                      );
                    }
                    return const SizedBox.shrink(); // Fixed: Added const
                  },
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  // Helper method to build action buttons
  Widget _buildActionButton({
    required IconData icon,
    required String label,
    required VoidCallback onTap,
  }) {
    return InkWell(
      onTap: onTap,
      borderRadius: BorderRadius.circular(8),
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0), // Fixed: Added const
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Icon(icon, color: Theme.of(context).primaryColor),
            const SizedBox(height: 4), // Fixed: Added const
            Text(
              label,
              style: TextStyle(
                fontSize: 11,
                fontWeight: FontWeight.w500,
                color: Theme.of(context).primaryColor,
              ),
            ),
          ],
        ),
      ),
    );
  }

  // Helper method to build review item
  Widget _buildReviewItem(BuildContext context, Review review) {
    // Determine emoji and color based on rating
    IconData emoji;
    Color color;

    // Mapping nota to accessibility levels
    if (review.nota >= 4) {
      emoji = Icons.sentiment_very_satisfied;
      color = Colors.green;
    } else if (review.nota >= 2) {
      emoji = Icons.sentiment_neutral;
      color = Colors.orange;
    } else {
      emoji = Icons.sentiment_very_dissatisfied;
      color = Colors.red;
    }

    // Get a "username" from userId (in a real app, you'd fetch the actual name)
    String authorName = "User #${review.idUsuario}";

    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0), // Fixed: Added const
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          // Accessibility emoji indicator
          Container(
            decoration: BoxDecoration(
              color: color.withOpacity(0.1),
              borderRadius: BorderRadius.circular(20),
            ),
            padding: const EdgeInsets.all(8), // Fixed: Added const
            child: Icon(emoji, color: color, size: 24),
          ),
          const SizedBox(width: 12), // Fixed: Added const

          // Review content
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      authorName,
                      style: const TextStyle(fontWeight: FontWeight.bold), // Fixed: Added const
                    ),
                    Text(
                      "${review.timestamp.day}/${review.timestamp.month}/${review.timestamp.year}",
                      style: const TextStyle(color: Colors.grey, fontSize: 12), // Fixed: Added const
                    ),
                  ],
                ),
                const SizedBox(height: 4), // Fixed: Added const
                Row(
                  children: List.generate(5, (index) {
                    return Icon(
                      index < review.nota ? Icons.star : Icons.star_border,
                      color: Colors.amber,
                      size: 18,
                    );
                  }),
                ),
                const SizedBox(height: 4), // Fixed: Added const
                Text(
                  review.avaliacao,
                  style: const TextStyle(fontSize: 13), // Fixed: Added const
                  maxLines: 3,
                  overflow: TextOverflow.ellipsis,
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}