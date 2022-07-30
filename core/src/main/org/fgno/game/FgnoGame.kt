package org.fgno.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.TimeUtils


class FgnoGame : ApplicationAdapter() {

    private lateinit var dropImage: Texture
    private lateinit var bucketImage: Texture
    private lateinit var dropSound: Sound
    private lateinit var rainMusic: Music
    private lateinit var batch: SpriteBatch
    private val bucket = Rectangle(800f / 2f - 64f / 2f, 20f, 64f, 64f)

    private val camera = OrthographicCamera()
    private val raindrops = com.badlogic.gdx.utils.Array<Rectangle>()
    private var lastDropTime = 0L

    override fun create() {
        dropImage = Texture(com.badlogic.gdx.Gdx.files.internal("droplet.png"))
        bucketImage = Texture(Gdx.files.internal("bucket.png"))
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        rainMusic.isLooping = true
        rainMusic.play()

        batch = SpriteBatch()
        camera.setToOrtho(false, 800f, 400f)

        spawnRaindrop()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)
        camera.update()
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, bucket.x, bucket.y);
        for (raindrop in raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y)
        }
        batch.end();

        if (Gdx.input.isTouched) {
            val touchPos = Vector3()
            touchPos[Gdx.input.x.toFloat(), Gdx.input.y.toFloat()] = 0f
            camera.unproject(touchPos)
            bucket.x = touchPos.x - 64 / 2
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0f) bucket.x = 0f;
        if (bucket.x > 800f - 64f) bucket.x = 800f - 64f;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        val iter: MutableIterator<Rectangle> = raindrops.iterator()
        while (iter.hasNext()) {
            val raindrop = iter.next()
            raindrop.y -= 200 * Gdx.graphics.deltaTime
            if (raindrop.y + 64 < 0) iter.remove()
            if (raindrop.overlaps(bucket)) {
                dropSound.play();
                iter.remove();
            }
        }
    }

    override fun dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
    }

    private fun spawnRaindrop() {
        val raindrop = Rectangle()
        raindrop.x = MathUtils.random(0, 800 - 64).toFloat()
        raindrop.y = 480f
        raindrop.width = 64f
        raindrop.height = 64f
        raindrops.add(raindrop)
        lastDropTime = TimeUtils.nanoTime()
    }
}