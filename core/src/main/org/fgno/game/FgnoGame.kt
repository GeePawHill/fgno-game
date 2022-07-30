package org.fgno.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class FgnoGame : ApplicationAdapter() {

    private lateinit var dropImage: Texture
    private lateinit var bucketImage: Texture
    private lateinit var dropSound: Sound
    private lateinit var rainMusic: Music

    var batch: SpriteBatch? = null
    var img: Texture? = null

    override fun create() {
        dropImage = Texture(com.badlogic.gdx.Gdx.files.internal("droplet.png"))
        bucketImage  = Texture(Gdx.files.internal("bucket.png"))
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render() {
        ScreenUtils.clear(1f, 0f, 0f, 1f)
        batch!!.begin()
        batch!!.draw(img, 0f, 0f)
        batch!!.end()
    }

    override fun dispose() {
        batch!!.dispose()
        img!!.dispose()
    }
}