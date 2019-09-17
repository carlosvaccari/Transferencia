package br.com.cvaccari.moneytransfer.utils

import androidx.annotation.IntDef
import br.com.cvaccari.moneytransfer.custom.view.AvatarImageView
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


@IntDef(
    AvatarImageView.SHOW_INITIAL,
    AvatarImageView.SHOW_IMAGE
)
@Retention(RetentionPolicy.SOURCE)
annotation class StateIntDef