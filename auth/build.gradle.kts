plugins {
	kotlin("plugin.jpa")
}

dependencies {
	implementation(project(":common"))

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

	runtimeOnly("org.postgresql:postgresql")

	runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
}
