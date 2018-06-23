package blue.sparse.example

import blue.sparse.engine.SparseGame
import blue.sparse.engine.asset.Asset
import blue.sparse.engine.asset.model.WavefrontModelLoader
import blue.sparse.engine.render.camera.FirstPerson
import blue.sparse.engine.render.resource.Texture
import blue.sparse.engine.render.resource.shader.ShaderProgram
import blue.sparse.engine.render.scene.component.ModelComponent
import blue.sparse.math.vectors.floats.Vector3f

class SparseExampleGame : SparseGame() {

	// Load and compile the shader program.
	// First parameter is the asset of the fragment shader
	// Second parameter is the asset of the vertex shader
	// Third parameter (not present here) is the asset of the geometry shader
	val shader = ShaderProgram(Asset["shaders/basic.fs"], Asset["shaders/basic.vs"])

	val modelComponent: ModelComponent

	init {

		// Set the controller of the camera to first person.
		// This controller is a default in the engine along with PanOrbit
		// The controller is what controls the camera, that may mean accepting inputs or not.
		camera.controller = FirstPerson(camera)

		// Retrieve the asset for the cube model
		val modelAsset = Asset["models/cube.obj"]

		// Parse the model and upload it to the GPU.
		val model = WavefrontModelLoader.load(modelAsset)

		// Retrieve the texture asset
		val textureAsset = Asset["textures/developer.png"]

		// Load the texture to the GPU
		val texture = Texture(textureAsset)

		// Important note: Do not load the same texture/model/shader multiple times unless you actually need to for some reason.
		// You can reuse these resources instead of duplicating them.

		// Create a component out of the model and texture, components are what make up a scene.
		modelComponent = ModelComponent(model, arrayOf(texture))

		// By default, the model will be at 0,0,0 which will be inside the camera.
		// This line moves it 2 units away from the camera.
		modelComponent.transform.translate(Vector3f(0f, 0f, 2f))

		// Add the model component to the scene
		scene.add(modelComponent)

	}

	override fun update(delta: Float) {
		// Update the camera
		camera.update(delta)

		// Update the scene
		scene.update(delta)

		// Rotate the model around an axis by the delta
		modelComponent.transform.rotateDeg(Vector3f(1f), delta * 10f)
	}

	override fun render(delta: Float) {
		// Clear the screen from the previous frame
		engine.clear()

		// Render the scene with our camera and shader
		scene.render(delta, camera, shader)
	}
}