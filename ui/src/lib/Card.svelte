<script lang="ts">
  import { onDestroy } from 'svelte';
  import { Circle } from 'svelte-loading-spinners';

  export let serviceName: string = '';
  export let baseUrl: string = '';
  export let checkIntervallInSeconds: number = 15;

  let isHealthy = false;
  let time;
  let healthcheck;
  let interval;

  async function doHealthcheck() {
    // Add timeout to reduce flickerung in Browser since healthcheck is fast
    const res: Response = await fetch(`${baseUrl}/healthcheck`).then(
      (v) =>
        new Promise((resolve) => {
          setTimeout(() => {
            resolve(v);
          }, 500);
        })
    );

    if (res.ok) {
      isHealthy = true;
    } else {
      isHealthy = false;
    }
  }

  onDestroy(() => {
    clearInterval(interval);
  });

  $: {
    healthcheck = doHealthcheck();
    time = new Date().toLocaleTimeString();
    clearInterval(interval);
    interval = setInterval(() => {
      healthcheck = doHealthcheck();
      time = new Date().toLocaleTimeString();
    }, checkIntervallInSeconds * 1000);
  }
</script>

<section
  class="bg-white w-[250px] h-[250px] p-4 neumo-card flex flex-col items-center justify-between"
>
  <h1 class="uppercase text-center font-semibold">{serviceName}</h1>
  {#await healthcheck}
    <div>
      <Circle size="30" unit="px" />
    </div>
  {:then}
    {#if isHealthy}
      <div class="bg-green-600 p-8 text-lg text-gray-200 health-box healthy">
        <p class="text-center">OK</p>
      </div>
    {:else}
      <div class="bg-red-600 health-box unhealthy">
        <p class="text-center text-4xl">😢</p>
      </div>
    {/if}
  {:catch}
    <div class="bg-red-600 health-box unhealthy">
      <p class="text-center text-4xl">🤕</p>
    </div>
  {/await}
  <p class="text-sm text-gray-600">Last checked: {time}</p>
</section>

<style>
  .neumo-card {
    border-radius: 30px;
    box-shadow: 5px 5px 10px #b3b3b3, -5px -5px 10px #fff;
  }
  .health-box {
    width: 100px;
    @apply p-8 text-lg text-gray-200;
    border-radius: 10px;
  }
  .healthy {
    background: linear-gradient(145deg, #18ae4f, #149343);
    box-shadow: 3px 3px 10px #0f7033, -3px -3px 10px #1dd661;
  }
  .unhealthy {
    background: linear-gradient(145deg, #eb2929, #c62222);
    box-shadow: 3px 3px 10px #981a1a, -3px -3px 10px #ff3232;
  }
</style>
