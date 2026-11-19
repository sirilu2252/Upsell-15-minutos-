package com.example.ui.components

import android.graphics.Matrix
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.net.Uri
import android.view.Surface
import android.view.TextureView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.R
import com.example.ui.theme.LocalUpsellColors
import com.example.ui.theme.MonoFontFamily
import kotlinx.coroutines.delay

/**
 * Leitor de vídeo nativo integrado para a demonstração dos exercícios em vídeo
 * (empacotado em res/raw/video_exercicios_demo.mp4).
 */
@Composable
fun ExerciseDemoVideoPlayer(
    modifier: Modifier = Modifier,
    titleBadge: String = "DEMONSTRAÇÃO REAL EM VÍDEO",
    testTagPrefix: String = "demo_video",
) {
    val colors = LocalUpsellColors.current
    var showFullscreenDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Moldura elegante estilo dispositivo móvel escuro com borda dourada
        Surface(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .fillMaxWidth(),
            color = colors.obsidianBackground,
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.5.dp, colors.goldPrimary.copy(alpha = 0.75f)),
            shadowElevation = 8.dp,
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Barra superior discreta do dispositivo
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .size(width = 52.dp, height = 4.dp)
                        .clip(RoundedCornerShape(50))
                        .background(colors.slateBorder),
                )

                InlineTextureVideoSurface(
                    rawResId = R.raw.video_exercicios_demo,
                    titleBadge = titleBadge,
                    testTagPrefix = testTagPrefix,
                    onExpandFullscreen = { showFullscreenDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(9f / 16f)
                        .clip(RoundedCornerShape(16.dp)),
                )
            }
        }
    }

    if (showFullscreenDialog) {
        FullscreenVideoDialog(
            rawResId = R.raw.video_exercicios_demo,
            onDismiss = { showFullscreenDialog = false },
        )
    }
}

@Composable
private fun InlineTextureVideoSurface(
    rawResId: Int,
    titleBadge: String,
    testTagPrefix: String,
    onExpandFullscreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val colors = LocalUpsellColors.current

    var isPlaying by remember { mutableStateOf(false) }
    var isMuted by remember { mutableStateOf(false) }
    var isPrepared by remember { mutableStateOf(false) }
    var durationMs by remember { mutableIntStateOf(0) }
    var currentPositionMs by remember { mutableIntStateOf(0) }

    val mediaPlayer = remember(rawResId) {
        MediaPlayer().apply {
            isLooping = true
        }
    }

    DisposableEffect(lifecycleOwner, mediaPlayer) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE || event == Lifecycle.Event.ON_STOP) {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    isPlaying = false
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            try {
                if (mediaPlayer.isPlaying) mediaPlayer.stop()
            } catch (_: IllegalStateException) {
            }
            mediaPlayer.release()
        }
    }

    LaunchedEffect(isPlaying, isPrepared) {
        while (isPlaying && isPrepared) {
            try {
                currentPositionMs = mediaPlayer.currentPosition
            } catch (_: IllegalStateException) {
            }
            delay(250L)
        }
    }

    Box(
        modifier = modifier
            .background(colors.obsidianBackground)
            .testTag("${testTagPrefix}_container"),
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                TextureView(ctx).apply {
                    surfaceTextureListener = object : TextureView.SurfaceTextureListener {
                        override fun onSurfaceTextureAvailable(
                            surfaceTexture: SurfaceTexture,
                            width: Int,
                            height: Int,
                        ) {
                            try {
                                val surface = Surface(surfaceTexture)
                                mediaPlayer.reset()
                                val uri = Uri.parse("android.resource://${ctx.packageName}/$rawResId")
                                mediaPlayer.setDataSource(ctx, uri)
                                mediaPlayer.setSurface(surface)
                                mediaPlayer.isLooping = true
                                mediaPlayer.setOnPreparedListener { mp ->
                                    isPrepared = true
                                    durationMs = mp.duration.coerceAtLeast(1)
                                    val vw = mp.videoWidth
                                    val vh = mp.videoHeight
                                    if (vw > 0 && vh > 0) {
                                        applyCenterCropOrFitMatrix(this@apply, width, height, vw, vh)
                                    }
                                    val vol = if (isMuted) 0f else 1f
                                    mp.setVolume(vol, vol)
                                    // Mostra o primeiro frame nítido mesmo antes de iniciar
                                    mp.seekTo(100)
                                }
                                mediaPlayer.prepareAsync()
                            } catch (_: Exception) {
                                isPrepared = false
                            }
                        }

                        override fun onSurfaceTextureSizeChanged(
                            surfaceTexture: SurfaceTexture,
                            width: Int,
                            height: Int,
                        ) {
                            if (isPrepared && mediaPlayer.videoWidth > 0 && mediaPlayer.videoHeight > 0) {
                                applyCenterCropOrFitMatrix(
                                    this@apply,
                                    width,
                                    height,
                                    mediaPlayer.videoWidth,
                                    mediaPlayer.videoHeight,
                                )
                            }
                        }

                        override fun onSurfaceTextureDestroyed(surfaceTexture: SurfaceTexture): Boolean {
                            try {
                                if (mediaPlayer.isPlaying) {
                                    mediaPlayer.pause()
                                    isPlaying = false
                                }
                                mediaPlayer.setSurface(null)
                            } catch (_: IllegalStateException) {
                            }
                            return true
                        }

                        override fun onSurfaceTextureUpdated(surfaceTexture: SurfaceTexture) = Unit
                    }
                }
            },
        )

        // Camada clicável para reproduzir/pausar
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    if (!isPrepared) return@clickable
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                        isPlaying = false
                    } else {
                        mediaPlayer.start()
                        isPlaying = true
                    }
                }
                .testTag("${testTagPrefix}_toggle_play"),
        )

        // Etiqueta discreta no topo
        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp),
            color = colors.obsidianBackground.copy(alpha = 0.82f),
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, colors.goldPrimary.copy(alpha = 0.7f)),
        ) {
            Text(
                text = titleBadge,
                style = MaterialTheme.typography.labelSmall,
                color = colors.goldHighlight,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            )
        }

        // Botão central de Play quando pausado
        if (!isPlaying) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(colors.obsidianBackground.copy(alpha = 0.78f))
                    .border(2.dp, colors.goldPrimary, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Reproduzir vídeo demonstrativo",
                    tint = colors.goldHighlight,
                    modifier = Modifier.size(38.dp),
                )
            }
        }

        // Barra inferior com controlos (Play/Pause, Progresso, Áudio e Ecrã Inteiro)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            colors.obsidianBackground.copy(alpha = 0.92f),
                        ),
                    ),
                )
                .padding(horizontal = 10.dp, vertical = 8.dp),
        ) {
            if (durationMs > 0) {
                Slider(
                    value = currentPositionMs.toFloat().coerceIn(0f, durationMs.toFloat()),
                    onValueChange = { newPos ->
                        if (isPrepared) {
                            currentPositionMs = newPos.toInt()
                            mediaPlayer.seekTo(currentPositionMs)
                        }
                    },
                    valueRange = 0f..durationMs.toFloat(),
                    colors = SliderDefaults.colors(
                        thumbColor = colors.goldHighlight,
                        activeTrackColor = colors.goldPrimary,
                        inactiveTrackColor = colors.textPrimary.copy(alpha = 0.28f),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(22.dp),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            if (!isPrepared) return@IconButton
                            if (mediaPlayer.isPlaying) {
                                mediaPlayer.pause()
                                isPlaying = false
                            } else {
                                mediaPlayer.start()
                                isPlaying = true
                            }
                        },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("${testTagPrefix}_play_button"),
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                            contentDescription = if (isPlaying) "Pausar vídeo" else "Reproduzir vídeo",
                            tint = colors.goldHighlight,
                        )
                    }

                    Text(
                        text = "${formatTimeMs(currentPositionMs)} / ${formatTimeMs(durationMs)}",
                        fontFamily = MonoFontFamily,
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.textPrimary,
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            isMuted = !isMuted
                            if (isPrepared) {
                                val vol = if (isMuted) 0f else 1f
                                mediaPlayer.setVolume(vol, vol)
                            }
                        },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("${testTagPrefix}_mute_button"),
                    ) {
                        Icon(
                            imageVector = if (isMuted) Icons.AutoMirrored.Filled.VolumeOff else Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = if (isMuted) "Ativar som" else "Silenciar som",
                            tint = colors.textPrimary,
                        )
                    }

                    IconButton(
                        onClick = {
                            if (mediaPlayer.isPlaying) {
                                mediaPlayer.pause()
                                isPlaying = false
                            }
                            onExpandFullscreen()
                        },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("${testTagPrefix}_fullscreen_button"),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Fullscreen,
                            contentDescription = "Expandir vídeo em ecrã inteiro",
                            tint = colors.goldHighlight,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FullscreenVideoDialog(
    rawResId: Int,
    onDismiss: () -> Unit,
) {
    val colors = LocalUpsellColors.current
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.obsidianBackground)
                .statusBarsPadding()
                .navigationBarsPadding(),
        ) {
            InlineTextureVideoSurface(
                rawResId = rawResId,
                titleBadge = "MÉTODO DOS 15 MINUTOS",
                testTagPrefix = "fullscreen_demo_video",
                onExpandFullscreen = onDismiss,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )

            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .background(colors.obsidianBackground.copy(alpha = 0.85f))
                    .border(1.dp, colors.goldPrimary, CircleShape)
                    .minimumInteractiveComponentSize()
                    .testTag("close_fullscreen_video_button"),
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Fechar ecrã inteiro",
                    tint = colors.goldHighlight,
                )
            }
        }
    }
}

private fun applyCenterCropOrFitMatrix(
    textureView: TextureView,
    viewWidth: Int,
    viewHeight: Int,
    videoWidth: Int,
    videoHeight: Int,
) {
    if (viewWidth == 0 || viewHeight == 0 || videoWidth == 0 || videoHeight == 0) return
    val viewRatio = viewWidth.toFloat() / viewHeight.toFloat()
    val videoRatio = videoWidth.toFloat() / videoHeight.toFloat()

    val scaleX: Float
    val scaleY: Float
    if (videoRatio > viewRatio) {
        scaleX = 1f
        scaleY = viewRatio / videoRatio
    } else {
        scaleX = videoRatio / viewRatio
        scaleY = 1f
    }

    val matrix = Matrix()
    matrix.setScale(scaleX, scaleY, viewWidth / 2f, viewHeight / 2f)
    textureView.setTransform(matrix)
}

private fun formatTimeMs(ms: Int): String {
    val totalSeconds = (ms / 1000).coerceAtLeast(0)
    val mins = totalSeconds / 60
    val secs = totalSeconds % 60
    return "${mins.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}"
}
