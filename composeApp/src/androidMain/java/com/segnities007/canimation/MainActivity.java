package com.segnities007.canimation;

import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;

import kotlin.Unit;

public final class MainActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);

        ComponentActivityKt.setContent(this, composableLambda -> {
            AppKt.App();
            return Unit.INSTANCE;
        });
    }
}
