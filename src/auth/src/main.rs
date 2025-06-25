/*
 * Copyright 2025 uuhnaut69
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

use axum::Router;
use dotenvy::dotenv;
use std::env;
use tower::ServiceBuilder;
use tower_http::{compression::CompressionLayer, decompression::RequestDecompressionLayer};
use tracing_subscriber::layer::SubscriberExt;
use tracing_subscriber::registry;
use tracing_subscriber::util::SubscriberInitExt;

#[tokio::main]
async fn main() {
    dotenv().unwrap();

    registry()
        .with(
            tracing_subscriber::EnvFilter::try_from_default_env()
                .unwrap_or_else(|_| format!("{}=debug", env!("CARGO_CRATE_NAME")).into()),
        )
        .with(tracing_subscriber::fmt::layer())
        .init();

    let api_routes = Router::new();

    let app: Router = Router::new().nest("/api", api_routes).layer(
        ServiceBuilder::new()
            .layer(RequestDecompressionLayer::new())
            .layer(CompressionLayer::new()),
    );

    let server_port = env::var("PORT").unwrap_or_else(|_| "3000".to_string());
    let listener = tokio::net::TcpListener::bind(format!("0.0.0.0:{}", server_port))
        .await
        .unwrap();

    tracing::info!("🚀 Server listening on {}", listener.local_addr().unwrap());
    axum::serve(listener, app).await.unwrap();
}
