package blue.sparse.example

import blue.sparse.engine.SparseEngine
import blue.sparse.engine.window.Window

fun main(args: Array<String>) {
	// Parse the arguments, default to 1280x720 window size
	val width = args.getOrNull(0)?.toIntOrNull() ?: 1280
	val height = args.getOrNull(1)?.toIntOrNull() ?: 720

	// Create a window with the provided width and height
	val window = Window(width, height) {
		// Make the window resizable
		resizable()

		// Set the icon of the window to the sparse icon. This file is included in the engine.
		// If you want your own icon, put a PNG in the resources folder and change it here.
		icon("sparse_icon_64.png")

		// This actually defaults to true, so this line doesn't actually do anything.
		// If you want to disable vSync, though...
		vSync(true)
	}

	// Start the game with the window. If targetFrameRate is 0, it makes the frame rate unlimited (unless vSync is enabled)
	SparseEngine.start(window, SparseExampleGame::class, 0.0)
}