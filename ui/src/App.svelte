<script lang="ts">
  import Card from './lib/Card.svelte';
  import './app.css';

  const services: { name: string; baseUrl: string }[] = [
    {
      name: 'dotnet-api',
      baseUrl: import.meta.env.VITE_DOTNET_URL,
    },
    {
      name: 'dotnet-minimal-api',
      baseUrl: import.meta.env.VITE_DOTNET_MINIMAL_URL,
    },
    {
      name: 'go-echo',
      baseUrl: import.meta.env.VITE_GO_URL,
    },
    {
      name: 'kotlin-ktor',
      baseUrl: import.meta.env.VITE_KTOR_URL,
    },
    {
      name: 'kotlin-spring',
      baseUrl: import.meta.env.VITE_SPRING_URL,
    },
    {
      name: 'rust-rocket',
      baseUrl: import.meta.env.VITE_RUST_URL,
    },
  ];

  let healthCheckInterval = '10';
</script>

<main class="main-body">
  <div
    class="py-8 px-16 w-full flex flex-col items-center justify-center h-full"
  >
    <h1 class="text-center text-6xl mb-10 uppercase font-black heading">
      Backend Status
    </h1>
    <div class="mb-10 text-lg">
      <label for="seconds">Do healthcheck every</label>
      <select
        bind:value={healthCheckInterval}
        name="seconds"
        id="seconds"
        autocomplete="off"
      >
        <option value="5" selected>5</option>
        <option value="10">10</option>
        <option value="15">15</option>
        <option value="20">20</option>
        <option value="25">25</option>
        <option value="30">30</option>
      </select>
      <label for="seconds">seconds</label>
    </div>
    <div class="grid grid-cols-3 gap-6">
      {#each services as service}
        <Card
          serviceName={service.name}
          baseUrl={service.baseUrl}
          checkIntervallInSeconds={parseInt(healthCheckInterval)}
        />
      {/each}
    </div>
  </div>
</main>

<style>
  .main-body {
    width: 100vw;
    height: 100vh;
    background: rgba(237, 242, 247);
  }

  .heading {
    text-shadow: 0 0 40px gray;
  }
</style>
