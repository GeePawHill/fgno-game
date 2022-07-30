package org.fgno.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.ScreenUtils

class FgnoGame : ApplicationAdapter() {

    private lateinit var dropImage: Texture
    private lateinit var bucketImage: Texture
    private lateinit var dropSound: Sound
    private lateinit var rainMusic: Music
    private lateinit var batch:SpriteBatch
    private val bucket = Rectangle(800f/2f-64f/2f,20f,64f,64f)

    private val camera = OrthographicCamera()

    override fun create() {
        dropImage = Texture(com.badlogic.gdx.Gdx.files.internal("droplet.png"))
        bucketImage  = Texture(Gdx.files.internal("bucket.png"))
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        rainMusic.isLooping=true
        rainMusic.play()

        batch = SpriteBatch()
        camera.setToOrtho(false,800f,400f)
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)
        camera.update()
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, bucket.x, bucket.y);
        batch.end();
    }

    override fun dispose() {
    }
}