<script lang="ts">
    import { goto } from '$app/navigation';
	import type { Action } from 'svelte/action';
    import { load, Store } from '@tauri-apps/plugin-store';
    import { onMount } from 'svelte';

	const center: Action = (node) => {
		$effect(() => {
            node.onfocus = () => {
                node.scrollIntoView({ behavior: 'smooth', block: 'center' })
            }
		});
	};

    let store: undefined | Store = undefined;

    onMount(async () => {
        store = await load('entries')
    })

    async function handleSubmit(e: SubmitEvent) {
        e.preventDefault();

        const formData = new FormData(e.target! as HTMLFormElement);
        
        // console.log(Object.fromEntries(formData))

        let now = new Date();
        await store?.set(now.toISOString(), Object.fromEntries(formData))
        await store?.save()

        goto("/vault")
    }
</script>

<form class="flex flex-col justify-center gap-5 pt-30 pb-60" onsubmit={handleSubmit}>
    <p class="font-[lucon]">I feel</p>
    <textarea required name="feel" use:center class="focus:outline-0 resize-y font-[lucon] text-base inverted"></textarea>
    <p class="font-[lucon]">because</p>
    <textarea required name="because" use:center class="focus:outline-0 resize-y font-[lucon] text-base inverted h-30"></textarea>
    <p class="font-[lucon]">, but its ok because</p>
    <textarea required name="okbc" use:center class="focus:outline-0 resize-y font-[lucon] text-base inverted h-20"></textarea>
    <p class="font-[lucon]">. to feel better, i did</p>
    <textarea name="feelbetter" use:center class="focus:outline-0 resize-y font-[lucon] text-base inverted"></textarea>
    <div class="py-5">
        <p class="font-[lucon] inline pr-2">which</p>
        <select name="worked" class="focus:outline-0 font-[lucon] text-base inverted h-10 px-2 w-fit inline">
            <option value="worked">worked</option>
            <option value="didnt work">didnt work</option>
            <option value="kind of worked">kind of worked</option>
        </select>
    </div>
    <div>
        <button class="inverted font-[lucon] text-base h-10 px-7 w-fit">done</button>
        <button class="font-[lucon] text-base h-10 px-7 w-fit" onclick={() => goto("/")}>back</button>
    </div>
</form>