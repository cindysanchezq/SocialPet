package com.example.socialpet

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.annotation.OptIn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(
    videoUrl: String,
    isFullscreen: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val activity = context as ComponentActivity

    // Reproductor
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            prepare()
            playWhenReady = false
        }
    }

    // Liberar el reproductor al salir
    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> exoPlayer.pause()
                Lifecycle.Event.ON_DESTROY -> exoPlayer.release()
                else -> {}
            }
        }

        activity.lifecycle.addObserver(observer)
        onDispose {
            activity.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    // Video embebido
    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = true
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
                setControllerAutoShow(true)
                setControllerHideOnTouch(false)
                setFullscreenButtonClickListener {
                    isFullscreen.value = !isFullscreen.value
                }
            }
        },
        modifier = modifier
    )
}

// Botón ⛶ de fullscreen
fun PlayerView.setFullscreenButtonClickListener(onClick: () -> Unit) {
    val fullscreenButtonId = resources.getIdentifier("exo_fullscreen", "id", context.packageName)
    findViewById<android.view.View>(fullscreenButtonId)?.setOnClickListener { onClick() }
}
