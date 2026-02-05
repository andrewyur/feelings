<script lang="ts">
    import { page } from "$app/state";
    import { load, Store } from "@tauri-apps/plugin-store";
    import { onMount } from "svelte";
    import type { Entry } from "../+page.svelte";
    import { goto } from "$app/navigation";
    import type { FormEventHandler } from "svelte/elements";

    let entry: undefined | Entry = $state()

    let key = page.params.id!;
    let date = new Date(key);
    let store: undefined | Store = undefined;

    onMount(async () => {
        store = await load('entries')

        entry = await store.get(key)
    })

    const handleInput: FormEventHandler<HTMLSelectElement> = async (e) => {
        //@ts-expect-error
        const value = e.target.value;

        if(entry) {
            entry.worked = value
            await store?.set(key, entry)
        }
    }
</script>

<button class="font-[lucon] inverted w-full h-10 my-10" onclick={() => goto("/")}>home</button>

<h1 class="font-[lucon] mb-15">{date.getMonth() + 1}/{date.getDate()}/{date.getFullYear()}</h1>

<div class="flex flex-col justify-center gap-5 pb-30">
    <p class="font-[lucon]">I feel</p>
    <p class="focus:outline-0 resize-y font-[lucon] text-base">{entry?.feel}</p>
    <p class="font-[lucon]">because</p>
    <p class="focus:outline-0 resize-y font-[lucon] text-base">{entry?.because}</p>
    <p class="font-[lucon]">, but its ok because</p>
    <p class="focus:outline-0 resize-y font-[lucon] text-base">{entry?.okbc}</p>
    <p class="font-[lucon]">. to feel better, i</p>
    <p class="focus:outline-0 resize-y font-[lucon] text-base">{entry?.feelbetter}</p>
    <div class="">
        <p class="font-[lucon] inline pr-2">which</p>
        <select oninput={handleInput} class="focus:outline-0 font-[lucon] text-base inverted h-10 px-2 w-fit inline">
            {#each ["worked", "didnt work", "kind of worked", "i havent done yet"] as value}
                <option {value} selected={entry?.worked == value}>{value}</option>
            {/each}
        </select>
    </div>
</div>